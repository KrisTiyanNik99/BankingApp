package main.models;

import java.math.BigDecimal;
import java.util.List;

public class User {
    private final int id;
    private String username, password, email;
    private BigDecimal balance;
    private List<Transaction> transactions;
    private List<Card> cards;

    public User(int id, String username, String password, String email,
                BigDecimal balance, List<Transaction> transactions, List<Card> cards) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
        this.transactions = transactions;
        this.cards = cards;
    }

    public String getUsername() {
        return username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                ", cards=" + cards +
                '}';
    }
}