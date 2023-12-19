package dev.camquevedo.bankInc.Repositories.v1.Cards.Card.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Cards.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepositoryInterface extends JpaRepository<Card, Long> {}
