package bg.fmi.cms.service.impl;

import bg.fmi.cms.keys.CipherUtils;
import bg.fmi.cms.model.Bin;
import bg.fmi.cms.model.Card;
import bg.fmi.cms.model.SymmetricKey;
import bg.fmi.cms.model.constats.CardStatus;
import bg.fmi.cms.model.constats.KeyUsage;
import bg.fmi.cms.repo.BinRepository;
import bg.fmi.cms.repo.CardRepository;
import bg.fmi.cms.repo.KeyRepository;
import bg.fmi.cms.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    private BinRepository binRepository;

    @Override
    public void updateCardStatus(Long cardId, CardStatus status) {
        Card card = cardRepository.findById(cardId).get();
        card.setCardStatus(status);
        cardRepository.save(card);
    }


    @Override
    public boolean authorize(Card card, String pinBlock) {
        SymmetricKey cardPinKey = keyRepository.findSymmetricKeyByBinAndKeyUsage(card.getBin(), KeyUsage.CARD_PIN_KEY);
        SymmetricKey acPinKey = keyRepository.findSymmetricKeyByBinAndKeyUsage(card.getBin(), KeyUsage.AUTHORIZATION_PIN_KEY);
        return CipherUtils.checkPin(card.getPinBlock(), pinBlock, acPinKey.getKeyValue(), cardPinKey.getKeyValue());
    }

    @Override
    public boolean authorize(String pan, String pinBlock) {
        Card card = cardRepository.getByPan(pan).orElseThrow((() -> new RuntimeException("Pan Not Found")));
        return this.authorize(card, pinBlock);
    }

    @Override
    public void changePin(Long id, String pin) {
        Card card = cardRepository.findById(id).get();
        SymmetricKey panDecryptionKey = keyRepository.findSymmetricKeyByBinAndKeyUsage(card.getBin(), KeyUsage.CARD_PAN_KEY);
        SymmetricKey pinEncryptionKey = keyRepository.findSymmetricKeyByBinAndKeyUsage(card.getBin(), KeyUsage.CARD_PIN_KEY);
        card.setPinBlock(CipherUtils.pinBlock(pinEncryptionKey.getKeyValue(), pin.substring(0, 4), CipherUtils.getClearPan(card.getPan(), panDecryptionKey.getKeyValue())));
        cardRepository.save(card);
    }

    @Override
    public Card getByClearPan(String pan) {
        String binStr = pan.substring(0, 6);
        Bin bin = binRepository.getByBin(binStr).orElseThrow(() -> new RuntimeException("no bin for pan is registered"));
        SymmetricKey cardPanKey = keyRepository.findSymmetricKeyByBinAndKeyUsage(bin, KeyUsage.CARD_PAN_KEY);
        Optional<Card> byPan = cardRepository.getByPan(CipherUtils.encryptPan(pan, cardPanKey.getKeyValue()));
        return byPan.orElseThrow(() -> new RuntimeException("no such pan"));
    }

    @Override
    public Card addCard(Card card) {
        SymmetricKey cardEncKey = keyRepository.findSymmetricKeyByBinAndKeyUsage(card.getBin(), KeyUsage.CARD_PAN_KEY);
        SymmetricKey cardPinKey = keyRepository.findSymmetricKeyByBinAndKeyUsage(card.getBin(), KeyUsage.CARD_PIN_KEY);
        card.setCvv(CipherUtils.pinBlock(cardPinKey.getKeyValue(), pad(card.getCvv()), card.getPan()));
        card.setPinBlock(CipherUtils.pinBlock(cardPinKey.getKeyValue(), card.getPinBlock(), card.getPan()));
        card.setPan(CipherUtils.encryptPan(card.getPan(), cardEncKey.getKeyValue()));
        card.setCardStatus(CardStatus.PENDING);
        card.setExpiryDate(card.getExpiryDate());
        return card;
    }

    @Override
    public Card getById(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new RuntimeException(id + ""));
        card.setPan(CipherUtils.getClearPan(card.getPan(), keyRepository.findSymmetricKeyByBinAndKeyUsage(card.getBin(), KeyUsage.CARD_PAN_KEY).getKeyValue()));
        card.setPinBlock("");
        return card;
    }

    @Override
    public List<Card> getAll() {
        // TODO fix retrieval of pins
        return (List<Card>) cardRepository.findAll();
    }

    public List<Card> getClearCardDetails() {
        Iterable<Card> all = cardRepository.findAll();
        all.forEach(this::maskCard);
        return (List<Card>) all;
    }

    private String pad(String cvv) {
        return "0" + cvv;
    }

    private void maskCard(Card card) {
        String pan = CipherUtils.getClearPan(card.getPan(), keyRepository.findSymmetricKeyByBinAndKeyUsage(card.getBin(), KeyUsage.CARD_PAN_KEY).getKeyValue());
        StringBuilder builder = new StringBuilder();
        builder.append(pan, 0, 4);
        builder.append(" ");
        builder.append(pan, 4, 6);
        builder.append("** **** ");
        builder.append(pan, 12, 16);
        card.setPan(builder.toString());
        card.setPinBlock("****");
        card.setCvv("***");
    }

}
