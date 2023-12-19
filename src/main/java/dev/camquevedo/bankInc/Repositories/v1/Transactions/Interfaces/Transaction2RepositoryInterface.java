package dev.camquevedo.bankInc.Repositories.v1.Transactions.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Transactions.Transaction;

import java.util.List;

public interface Transaction2RepositoryInterface {
    List<Transaction> findAll();
    List<Transaction> findById(long id);
    List<Transaction> findByNumber(long number);
    long create(Transaction newCard);
}
