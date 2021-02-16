package bg.fmi.cms.service;

import bg.fmi.cms.model.Card;
import bg.fmi.cms.model.constats.CardStatus;

import java.util.List;

public interface CardService {
    void updateCardStatus(Long cardId, CardStatus status);

    boolean authorize(Card card, String pinBlock);

    boolean authorize(String pan, String pinBlock);

    void changePin(Long card, String pin);

    Card getByClearPan(String pan);

    Card addCard(Card card);

    Card getById(Long id);

    List<Card> getAll();

    List<Card> getClearCardDetails();
}
