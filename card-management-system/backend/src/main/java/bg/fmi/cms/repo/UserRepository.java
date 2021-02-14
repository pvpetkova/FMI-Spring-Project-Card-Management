package bg.fmi.cms.repo;

import bg.fmi.cms.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getByUsername(String userName);
}
