package bg.fmi.cms.service.impl;

import bg.fmi.cms.keys.CipherUtils;
import bg.fmi.cms.model.Card;
import bg.fmi.cms.model.Key;
import bg.fmi.cms.model.constats.CardStatus;
import bg.fmi.cms.model.constats.KeyUsage;
import bg.fmi.cms.repo.CardRepository;
import bg.fmi.cms.repo.KeyRepository;
import bg.fmi.cms.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private KeyRepository keyRepository;

    @Override
    public void updateCardStatus(Long cardId, CardStatus status) {
        Card card = cardRepository.findById(cardId).get();
        card.setCardStatus(status);
        cardRepository.save(card);
    }

    @Override
    public boolean authorize(Card card, String pinBlock) {
        Key cardPinKey = keyRepository.findKeyByBinAndKeyUsage(card.getBin(), KeyUsage.PIN);
        Key acPinKey = keyRepository.findKeyByBinAndKeyUsage(card.getBin(), KeyUsage.DECRYPT);
        return CipherUtils.checkPin(card.getPinBlock(), pinBlock, acPinKey.getKeyValue(), cardPinKey.getKeyValue());
    }

    @Override
    public void changePin(Long id, String pin) {
        Card card = cardRepository.findById(id).get();
        Key panDecryptionKey = keyRepository.findKeyByBinAndKeyUsage(card.getBin(), KeyUsage.ENCRYPT);
        Key pinEncryptionKey = keyRepository.findKeyByBinAndKeyUsage(card.getBin(), KeyUsage.PIN);
        card.setPinBlock(CipherUtils.pinBlock(pinEncryptionKey.getKeyValue(), pin, CipherUtils.getClearPan(card.getPan(), panDecryptionKey.getKeyValue())));
        cardRepository.save(card);
    }


}
