package st.projects.assignments.billogram.service.discount.generator;

import org.springframework.stereotype.Service;
import st.projects.assignments.billogram.repository.model.Campaign;
import st.projects.assignments.billogram.service.campaign.CampaignService;

@Service
public class SimpleCodeGenerator implements CodeGeneratorStrategy {

    public SimpleCodeGenerator(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    final CampaignService campaignService;

    @Override
    public String generateCode(Campaign campaign) throws OutOfStockException {
        final int serialPart = campaignService.allocate(campaign);
        final String code = campaign.id + "-" + campaign.name + "-" + serialPart;
        return code;
    }
}
