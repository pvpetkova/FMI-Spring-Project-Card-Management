package bg.fmi.cms.repo;

import bg.fmi.cms.model.Key;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends CrudRepository<Key, Long> {
}
