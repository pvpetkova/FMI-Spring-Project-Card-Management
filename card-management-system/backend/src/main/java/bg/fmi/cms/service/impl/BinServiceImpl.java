package bg.fmi.cms.service.impl;

import bg.fmi.cms.keys.KeyGenerator;
import bg.fmi.cms.model.Bin;
import bg.fmi.cms.model.Key;
import bg.fmi.cms.model.constats.KeyUsage;
import bg.fmi.cms.repo.BinRepository;
import bg.fmi.cms.repo.CardRepository;
import bg.fmi.cms.repo.KeyRepository;
import bg.fmi.cms.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    @Autowired
    private BinRepository binRepository;

    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void add(Bin bin) {
        List<Key> keys = new LinkedList<>();
        keys.add(KeyGenerator.generateDesEDEKey(KeyUsage.PIN, bin));
        keys.add(KeyGenerator.generateDesEDEKey(KeyUsage.ENCRYPT, bin));
        keys.add(KeyGenerator.generateDesEDEKey(KeyUsage.DECRYPT, bin));
        binRepository.save(bin);
        keyRepository.saveAll(keys);
    }

    @Override
    public void delete(Bin bin) {
        binRepository.delete(bin);
    }

    @Override
    public void delete(Long binId) {
        binRepository.deleteById(binId);
    }

    @Override
    public void changeKeys(Bin bin) {
        List<Key> keys = keyRepository.findKeyByBin(bin);
        for(Key key: keys){
            key = KeyGenerator.generateDesEDEKey(key.getKeyUsage(), bin);
        }
        keyRepository.saveAll(keys);

    }
}
