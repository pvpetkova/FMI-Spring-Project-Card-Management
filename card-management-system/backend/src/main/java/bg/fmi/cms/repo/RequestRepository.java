package bg.fmi.cms.repo;

import bg.fmi.cms.model.Request;
import bg.fmi.cms.model.constats.RequestStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
    public List<Request> findAllByRequestStatus(RequestStatus status);
}
