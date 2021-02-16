package bg.fmi.cms.repo;

import bg.fmi.cms.model.Bin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BinRepository extends CrudRepository<Bin, Long> {
    Optional<Bin> getByBin(String bin);
}
