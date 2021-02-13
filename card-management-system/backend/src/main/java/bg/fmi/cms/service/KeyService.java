package bg.fmi.cms.service;

import bg.fmi.cms.model.Key;

public interface KeyService {
    Key getById(Long id);
    void delete(Long id);
    Iterable<Key> getAll();
    void addKey(Key key);

}
