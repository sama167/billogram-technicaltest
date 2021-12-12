package st.projects.assignments.billogram.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import st.projects.assignments.billogram.repository.model.Brand;

public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {

    Brand findById(@Param("id") long id);
}
