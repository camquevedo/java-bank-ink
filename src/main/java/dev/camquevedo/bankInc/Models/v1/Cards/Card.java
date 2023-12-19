package dev.camquevedo.bankInc.Models.v1.Cards;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public long id;
    public long status;
    @Column(name = "product_id")
    public long productId;
    public long number;
    public long balance;
    public boolean type;
    @Column(name = "first_name")
    public String firstName;
    @Column(name = "last_name")
    public String lastName;
    @Column(name = "expiration_date")
    public LocalDate expirationDate;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Card() {
    }
    public Card(long number) {
        this.number = number;
    }

    public Card(
            long id,
            long status,
            long productId,
            long number,
            long balance,
            boolean type,
            String firstName,
            String lastName,
            LocalDate expirationDate
    ) {
        this.id = id;
        this.status = status;
        this.productId = productId;
        this.number = number;
        this.balance = balance;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.expirationDate = expirationDate;
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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    public void addBalance(long balance) {
        this.balance = balance;
    }
    public void setBalance(long incomingBalance) {
        this.balance += incomingBalance;
    }
    public void removeBalance(long incomingBalance) {
        this.balance += incomingBalance;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expiration_date) {
        this.expirationDate = expiration_date;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updated_at) {
        this.updatedAt = updated_at;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deleted_at) {
        this.deletedAt = deleted_at;
    }

    @Override
    public String toString() {
        return "Card [id=" + id
                + ", status=" + status
                + ", product_id=" + productId
                + ", status=" + number
                + ", status=" + balance
                + ", status=" + type
                + ", first_name=" + firstName
                + ", last_name=" + lastName
                + ", expirationDate=" + expirationDate
                + "]";
    }

    @JsonCreator
    public Card(String cardId) {
        this.id = 123456789;
        this.number = Long.parseLong((cardId));
    }
}
