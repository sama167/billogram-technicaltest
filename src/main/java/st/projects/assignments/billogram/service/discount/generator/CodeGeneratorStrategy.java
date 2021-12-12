package st.projects.assignments.billogram.service.discount.generator;

import st.projects.assignments.billogram.repository.model.Campaign;

public interface CodeGeneratorStrategy {

    String generateCode(Campaign campaign) throws OutOfStockException;
}
