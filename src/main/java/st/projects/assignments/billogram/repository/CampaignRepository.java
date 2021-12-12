package st.projects.assignments.billogram.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import st.projects.assignments.billogram.repository.model.Campaign;

public interface CampaignRepository extends PagingAndSortingRepository<Campaign, Long> {
}
