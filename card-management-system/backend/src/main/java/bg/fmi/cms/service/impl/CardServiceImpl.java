package bg.fmi.cms.service.impl;

import bg.fmi.cms.model.Card;
import bg.fmi.cms.model.constats.CardStatus;
import bg.fmi.cms.repo.CardRepository;
import bg.fmi.cms.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    public void updateCardStatus(Long cardId, CardStatus status) {
        Optional<Card> card = cardRepository.findById(cardId);
    }

    @Override
    public boolean authorize(Card card) {
        return false;
    }

    @Override
    public void changePin(String pin) {

    }
}
