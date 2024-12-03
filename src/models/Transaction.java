package models;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private final int id, userId;
    private final String cardNumber, transactionType;
    private final BigDecimal amount;
    private final Date data;

    public Transaction(int id, int userId, String cardNumber, String transactionType, BigDecimal amount, Date data) {
        this.id = id;
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.data = data;
    }
}
