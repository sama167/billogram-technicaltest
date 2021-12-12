package st.projects.assignments.billogram.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import st.projects.assignments.billogram.repository.model.Campaign;
import st.projects.assignments.billogram.repository.model.DiscountCode;
import st.projects.assignments.billogram.repository.model.User;

import java.util.List;

public interface DiscountCodeRepository extends PagingAndSortingRepository<DiscountCode, Long> {

    DiscountCode findByUserAndCampaign(User user, Campaign campaign);

    List<DiscountCode> findByCampaign(Campaign campaign);
}
