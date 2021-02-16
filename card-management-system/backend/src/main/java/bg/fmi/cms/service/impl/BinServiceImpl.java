package bg.fmi.cms.service.impl;

import bg.fmi.cms.keys.KeyGenerator;
import bg.fmi.cms.model.Bin;
import bg.fmi.cms.model.SymmetricKey;
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
        List<SymmetricKey> keys = new LinkedList<>();
        keys.add(KeyGenerator.generateDesEDEKey(KeyUsage.CARD_PIN_KEY, bin));
        keys.add(KeyGenerator.generateDesEDEKey(KeyUsage.CARD_PAN_KEY, bin));
        keys.add(KeyGenerator.generateDesEDEKey(KeyUsage.AUTHORIZATION_PIN_KEY, bin));
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
        List<SymmetricKey> keys = keyRepository.findSymmetricKeysByBin(bin);
        for (SymmetricKey key : keys) {
            key = KeyGenerator.generateDesEDEKey(key.getKeyUsage(), bin);
        }
        keyRepository.saveAll(keys);

    }

    @Override
    public List<Bin> getAll() {
        return (List<Bin>) binRepository.findAll();
    }
}
