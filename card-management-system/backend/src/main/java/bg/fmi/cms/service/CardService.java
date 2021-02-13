package bg.fmi.cms.service;

import bg.fmi.cms.model.Card;
import bg.fmi.cms.model.constats.CardStatus;

public interface CardService {
    void updateCardStatus(CardStatus status);
    boolean authorize(Card card);
    void changePin(String pin);
}
