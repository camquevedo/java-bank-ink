package dev.camquevedo.bankInc.Models.v1.Transactions;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RechargeBalance {
    public long balance;
    public long cardId;

    @JsonCreator
    public RechargeBalance(String cardId, String balance) {
        this.cardId = Long.parseLong(cardId);
        this.balance = Long.parseLong(cardId);
    }

    public RechargeBalance(long balance, long cardId) {
        this.balance = balance;
        this.cardId = cardId;
    }

    public RechargeBalance(String balance, long cardId) {
        this.balance = Long.parseLong(balance);
        this.cardId = cardId;
    }

    public RechargeBalance(long balance, String cardId) {
        this.balance = balance;
        this.cardId = Long.parseLong(cardId);
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
}
