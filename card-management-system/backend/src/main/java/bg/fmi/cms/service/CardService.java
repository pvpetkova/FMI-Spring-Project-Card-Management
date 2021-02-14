package bg.fmi.cms.service;

import bg.fmi.cms.model.Card;
import bg.fmi.cms.model.constats.CardStatus;
import org.springframework.stereotype.Service;

@Service
public interface CardService {
    void updateCardStatus(Long cardId, CardStatus status);

    boolean authorize(Card card, String pinBlock);

    void changePin(Long card, String pin);
}
