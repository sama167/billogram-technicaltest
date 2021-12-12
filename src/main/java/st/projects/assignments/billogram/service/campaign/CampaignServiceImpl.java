package st.projects.assignments.billogram.service.campaign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import st.projects.assignments.billogram.repository.BrandRepository;
import st.projects.assignments.billogram.repository.CampaignRepository;
import st.projects.assignments.billogram.repository.DiscountCodeRepository;
import st.projects.assignments.billogram.repository.model.Campaign;
import st.projects.assignments.billogram.repository.model.DiscountCode;
import st.projects.assignments.billogram.service.discount.generator.OutOfStockException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CampaignServiceImpl implements CampaignService {

    final static Logger LOGGER = LoggerFactory.getLogger(CampaignServiceImpl.class);

    final BrandRepository brandRepository;
    final CampaignRepository campaignRepository;
    final DiscountCodeRepository discountCodeRepository;
    final Map<Long, AtomicInteger> campaignsStore;

    public CampaignServiceImpl(BrandRepository brandRepository, CampaignRepository campaignRepository, DiscountCodeRepository discountCodeRepository) {
        this.brandRepository = brandRepository;
        this.campaignRepository = campaignRepository;
        this.discountCodeRepository = discountCodeRepository;
        campaignsStore = new ConcurrentHashMap<>();
        postStartUp();
    }

    public void postStartUp() {
        final Iterable<Campaign> campaigns = campaignRepository.findAll();
        for (Campaign campaign : campaigns) {
            final List<DiscountCode> allocatedCodes = discountCodeRepository.findByCampaign(campaign);
            campaignsStore.put(campaign.id, new AtomicInteger(campaign.number - allocatedCodes.size()));
            LOGGER.info("Available discount codes for campaign " + campaign + " is " + campaignsStore.get(campaign.id));
        }
    }

    @Override
    public void save(Campaign campaign) {
        campaign.brand = brandRepository.findById(campaign.brand.id);
        campaignRepository.save(campaign);
        campaignsStore.computeIfAbsent(campaign.id, ignore -> new AtomicInteger(campaign.number));
    }

    @Override
    public Iterable<Campaign> findAll() {
        return campaignRepository.findAll();
    }

    @Override
    public int allocate(Campaign campaign) throws OutOfStockException {
        final int numberOfAvailableCodes = campaignsStore.get(campaign.id).decrementAndGet();
        if (numberOfAvailableCodes < 0) {
            throw new OutOfStockException();
        }
        return numberOfAvailableCodes;
    }
}
