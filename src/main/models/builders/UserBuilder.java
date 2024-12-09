package main.models.builders;

import main.models.Card;
import main.models.Transaction;
import main.models.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
    We use a builder class so that we can safely create objects of the User class. Inside the builder class, there
    will be setters that will check the information and if it is null, will set default values.
*/
public class UserBuilder {
    private int id;
    private String username, password, email;
    private BigDecimal balance;
    private List<Transaction> transactions;
    private List<Card> cards;

    public UserBuilder(int id, String username, String password) {
        setId(id);
        setUsername(username);
        setPassword(password);
    }

    public UserBuilder setId(int id) {
        this.id = id;

        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;

        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;

        return this;
    }

    public UserBuilder setEmail(String email) {
        if (email.isBlank() || email.isEmpty()) {
            email = "Empty";
        }
        this.email = email;

        return this;
    }

    public UserBuilder setBalance(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            balance = BigDecimal.ZERO.setScale(2);
        }
        this.balance = balance;

        return this;
    }

    public UserBuilder setTransactions(List<Transaction> transactions) {
        transactions = checkForTransactions(transactions);
        this.transactions = transactions;

        return this;
    }

    public UserBuilder setCards(List<Card> cards) {
        cards = checkForCard(cards);
        this.cards = cards;

        return this;
    }

    public UserBuilder addElement(Card card) {
        this.cards = checkForCard(cards);
        this.cards.add(card);

        return this;
    }

    public UserBuilder addElement(Transaction transaction) {
        this.transactions = checkForTransactions(transactions);
        this.transactions.add(transaction);

        return this;
    }

    public User build() {
        return new User(id, username, password, email, balance, transactions, cards);
    }

    private List<Transaction> checkForTransactions(List<Transaction> transactions) {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }

        return transactions;
    }

    private List<Card> checkForCard(List<Card> cards) {
        if (cards == null) {
            cards = new ArrayList<>();
        }

        return cards;
    }
}
