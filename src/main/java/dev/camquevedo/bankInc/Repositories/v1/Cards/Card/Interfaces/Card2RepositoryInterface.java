package dev.camquevedo.bankInc.Repositories.v1.Cards.Card.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Cards.Card;

import java.util.List;

public interface Card2RepositoryInterface {
    List<Card> findAll();
    List<Card> findById(long id);
    List<Card> findByNumber(long number);
    long create(Card newCard);
}
