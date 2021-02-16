package bg.fmi.cms.repo;

import bg.fmi.cms.model.Bin;
import bg.fmi.cms.model.SymmetricKey;
import bg.fmi.cms.model.constats.KeyUsage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyRepository extends CrudRepository<SymmetricKey, Long> {
    SymmetricKey findSymmetricKeyByBinAndKeyUsage(Bin bin, KeyUsage keyUsage);
    List<SymmetricKey> findSymmetricKeysByBin(Bin bin);
}
