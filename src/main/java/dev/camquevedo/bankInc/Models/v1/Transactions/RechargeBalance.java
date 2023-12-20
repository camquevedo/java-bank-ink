package dev.camquevedo.bankInc.Models.v1.Transactions;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RechargeBalance {
    public long balance;
    public long cardId;

    public RechargeBalance(long balance, Integer cardId) {
        this.balance = balance;
        this.cardId = cardId;
    }

    public RechargeBalance(String balance, Integer cardId) {
        this.balance = Long.parseLong(balance);
        this.cardId = cardId;
    }

    public RechargeBalance(long balance, String cardId) {
        this.balance = balance;
        this.cardId = Integer.parseInt(cardId);
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "Card [card_id=" + cardId
                + ", balance=" + balance
                + "]";
    }

    @JsonCreator
    public RechargeBalance(String cardId, String balance) {
        this.cardId = Long.parseLong(cardId);
        this.balance = Long.parseLong(balance);
    }
}
