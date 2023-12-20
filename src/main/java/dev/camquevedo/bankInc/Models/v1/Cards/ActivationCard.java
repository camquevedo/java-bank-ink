package dev.camquevedo.bankInc.Models.v1.Cards;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ActivationCard {
    public long number;
    public String cardId;

    @Override
    public String toString() {
        return "Card [number=" + number
                + ", card_id=" + cardId
                + "]";
    }

    @JsonCreator
    public ActivationCard(String cardId) {
        this.cardId = cardId;
        this.number = Long.parseLong(cardId);
    }
}
