package bg.fmi.cms.service.impl;

import bg.fmi.cms.model.Key;
import bg.fmi.cms.repo.KeyRepository;
import bg.fmi.cms.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyServiceImpl implements KeyService {

    @Autowired
    KeyRepository keyRepository;

    @Override
    public Key getById(Long id) {
        return keyRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        keyRepository.deleteById(id);
    }

    @Override
    public Iterable<Key> getAll() {
        return keyRepository.findAll();
    }

    @Override
    public void addKey(Key key) {
        keyRepository.save(key);
    }
}
