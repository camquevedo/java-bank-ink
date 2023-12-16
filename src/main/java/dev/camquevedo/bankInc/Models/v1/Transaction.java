package dev.camquevedo.bankInc.Models.v1;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public long status;
    public boolean type;
    public long balance;
    public LocalTime created_at;
    public LocalTime updated_at;
    public LocalTime deleted_at;

    public Transaction() {
    }

    public Transaction(long id, long status, boolean type, long balance) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public LocalTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalTime updated_at) {
        this.updated_at = updated_at;
    }

    public LocalTime getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalTime deleted_at) {
        this.deleted_at = deleted_at;
    }
}
