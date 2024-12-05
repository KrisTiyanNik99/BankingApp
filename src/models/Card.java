package models;

import models.types.CardType;

public class Card {
    private int id;
    private final int user_id;
    private final String cardNumber;
    private final CardType cardType;

    public Card(int id, int user_id, String cardNumber, CardType cardType) {
        this.id = id;
        this.user_id = user_id;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
