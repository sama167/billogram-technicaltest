package st.projects.assignments.billogram.service.discount;

import org.springframework.stereotype.Service;
import st.projects.assignments.billogram.repository.CampaignRepository;
import st.projects.assignments.billogram.repository.DiscountCodeRepository;
import st.projects.assignments.billogram.repository.UserRepository;
import st.projects.assignments.billogram.repository.model.Campaign;
import st.projects.assignments.billogram.repository.model.DiscountCode;
import st.projects.assignments.billogram.repository.model.User;
import st.projects.assignments.billogram.service.discount.generator.CodeGeneratorFactory;
import st.projects.assignments.billogram.service.discount.generator.OutOfStockException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {
    final CodeGeneratorFactory codeGeneratorFactory;
    final DiscountCodeRepository discountCodeRepository;
    final CampaignRepository campaignRepository;
    final UserRepository userRepository;

    public DiscountServiceImpl(CodeGeneratorFactory codeGeneratorFactory,
                               DiscountCodeRepository discountCodeRepository,
                               CampaignRepository campaignRepository,
                               UserRepository userRepository) {
        this.codeGeneratorFactory = codeGeneratorFactory;
        this.discountCodeRepository = discountCodeRepository;
        this.campaignRepository = campaignRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String generateCode(long campaignId, long userId) throws OutOfStockException {
        final Optional<User> userOptional = userRepository.findById(userId);
        final User user = userOptional.orElseThrow(() -> new EntityNotFoundException(String.format("User with id %d not found.", userId)));

        final Optional<Campaign> campaignOptional = campaignRepository.findById(campaignId);
        final Campaign campaign = campaignOptional.orElseThrow(() -> new EntityNotFoundException(String.format("Campaign with id %d not found", campaignId)));

        final DiscountCode byUserAndCampaign = discountCodeRepository.findByUserAndCampaign(user, campaign);
        if (byUserAndCampaign != null) {
            return byUserAndCampaign.code;
        }

        final String code = codeGeneratorFactory.getCodeGenerator(campaign).generateCode(campaign);
        DiscountCode discountCode = new DiscountCode();
        discountCode.user = user;
        discountCode.campaign = campaign;
        discountCode.code = code;
        discountCodeRepository.save(discountCode);
        return code;
    }

    @Override
    public List<DiscountCode> findByCampaignId(long campaignId) {
        final Optional<Campaign> campaignOptional = campaignRepository.findById(campaignId);
        final Campaign campaign = campaignOptional.orElseThrow(() -> new EntityNotFoundException(String.format("Campaign with id %d not found", campaignId)));
        return discountCodeRepository.findByCampaign(campaign);
    }
}
