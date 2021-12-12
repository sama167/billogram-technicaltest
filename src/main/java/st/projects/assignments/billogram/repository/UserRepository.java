package st.projects.assignments.billogram.repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import st.projects.assignments.billogram.repository.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{
}
