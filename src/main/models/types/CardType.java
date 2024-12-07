package main.models.types;

public enum CardType {
    MASTER,
    VISA,
    PAYPAL,
    EBAY;

    CardType(){};

    public static CardType parseCardType(String type) {
        return CardType.valueOf(type.toUpperCase());
    }
}
