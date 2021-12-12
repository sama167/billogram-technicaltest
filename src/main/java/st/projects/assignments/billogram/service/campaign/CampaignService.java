package st.projects.assignments.billogram.service.campaign;

import st.projects.assignments.billogram.repository.model.Campaign;
import st.projects.assignments.billogram.service.discount.generator.OutOfStockException;


public interface CampaignService {
    void save(Campaign campaign);
    Iterable<Campaign> findAll();
    int allocate(Campaign campaign) throws OutOfStockException;
}
