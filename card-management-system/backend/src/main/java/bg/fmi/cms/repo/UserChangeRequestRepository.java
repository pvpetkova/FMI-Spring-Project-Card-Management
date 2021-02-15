package bg.fmi.cms.repo;

import bg.fmi.cms.model.UserChangeRequest;
import org.springframework.data.repository.CrudRepository;

public interface UserChangeRequestRepository extends CrudRepository<UserChangeRequest, Long> {
}
