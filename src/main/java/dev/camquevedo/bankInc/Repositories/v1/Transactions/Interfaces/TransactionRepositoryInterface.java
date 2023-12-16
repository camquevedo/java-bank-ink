package dev.camquevedo.bankInc.Repositories.v1.Transactions.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepositoryInterface extends JpaRepository<Transaction, Long> {}
