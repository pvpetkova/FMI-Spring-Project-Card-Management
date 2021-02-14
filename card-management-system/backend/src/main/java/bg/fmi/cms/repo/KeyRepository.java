package bg.fmi.cms.repo;

import bg.fmi.cms.model.Bin;
import bg.fmi.cms.model.Key;
import bg.fmi.cms.model.constats.KeyType;
import bg.fmi.cms.model.constats.KeyUsage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyRepository extends CrudRepository<Key, Long> {
    Key findKeyByBinAndKeyUsage(Bin bin, KeyUsage keyUsage);
    List<Key> findKeyByBin(Bin bin);
}
