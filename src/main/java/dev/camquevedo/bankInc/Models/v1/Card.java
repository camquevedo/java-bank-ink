package dev.camquevedo.bankInc.Models.v1;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public long status;
    public long number;
    public long balance;
    public boolean type;
    public long productId;
    public String first_name;
    public String last_name;
    public LocalTime expiration_date;
    public LocalTime created_at;
    public LocalTime updated_at;
    public LocalTime deleted_at;

    public Card() {
    }

    public Card(long productId) {
        this.productId = productId;
    }

    public Card(long id, long status, long number, long balance, boolean type, long productId, String first_name, String last_name, LocalTime expiration_date) {
        this.id = id;
        this.status = status;
        this.number = number;
        this.balance = balance;
        this.type = type;
        this.productId = productId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.expiration_date = expiration_date;
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

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalTime getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(LocalTime expiration_date) {
        this.expiration_date = expiration_date;
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
