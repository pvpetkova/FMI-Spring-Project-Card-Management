package bg.fmi.cms.service;

import bg.fmi.cms.model.Bin;

public interface BinService {
    void add(Bin bin);

    void delete(Bin bin);

    void delete(Long binId);

    void changeKeys(Bin bin);
}
