package st.projects.assignments.billogram.service.discount;

import st.projects.assignments.billogram.repository.model.DiscountCode;
import st.projects.assignments.billogram.service.discount.generator.OutOfStockException;

import java.util.List;

public interface DiscountService {
    String generateCode(long campaignId, long userId) throws OutOfStockException;

    List<DiscountCode> findByCampaignId(long campaignId);
}
