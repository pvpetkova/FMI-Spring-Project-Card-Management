package bg.fmi.cms.service.impl;

import bg.fmi.cms.model.SymmetricKey;
import bg.fmi.cms.repo.KeyRepository;
import bg.fmi.cms.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyServiceImpl implements KeyService {

    @Autowired
    KeyRepository keyRepository;

    @Override
    public SymmetricKey getById(Long id) {
        return keyRepository.findById(id).orElseGet(SymmetricKey::new);
    }

    @Override
    public void delete(Long id) {
        keyRepository.deleteById(id);
    }

    @Override
    public List<SymmetricKey> getAll() {
        return (List<SymmetricKey>) keyRepository.findAll();
    }

    @Override
    public void addKey(SymmetricKey key, String bin) {
        keyRepository.save(key);
    }

    @Override
    public void save(SymmetricKey key) {
        keyRepository.save(key);
    }
}
