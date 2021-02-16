package bg.fmi.cms.service;

import bg.fmi.cms.model.SymmetricKey;

import java.util.List;

public interface KeyService {
    SymmetricKey getById(Long id);
    void delete(Long id);
    List<SymmetricKey> getAll();
    void addKey(SymmetricKey key, String bin);
    void save(SymmetricKey key);
}
