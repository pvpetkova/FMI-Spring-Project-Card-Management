package bg.fmi.cms.repo;

import bg.fmi.cms.model.BinRange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinRangeRepository extends CrudRepository<BinRange, Long> {
}
