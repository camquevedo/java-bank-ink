package dev.camquevedo.bankInc.Services.v1.Transactions;

import dev.camquevedo.bankInc.Models.v1.Transaction;
import dev.camquevedo.bankInc.Repositories.v1.Transactions.Interfaces.TransactionRepositoryInterface;
import dev.camquevedo.bankInc.Services.v1.Transactions.Interfaces.TransactionServiceInterface;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements TransactionServiceInterface {
    private final  TransactionRepositoryInterface repository;
    public TransactionService(TransactionRepositoryInterface repository) {
        this.repository = repository;
    }


    public APIResponse getAll() throws BaseException {
        List<Transaction> repositoryResponse;
        try {
            repositoryResponse = repository.findAll();
//            repositoryResponse = repository.findAll();
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "transaction.service.getAll"
            );
        }
        return new APIResponse().withStatus(HttpStatus.OK)
                .withData(repositoryResponse)
                .withMessage("transactions.service.getAll");
    }

    public APIResponse getById(Long id) throws BaseException {
        Optional<Transaction> repositoryResponse;
        try {
            repositoryResponse = repository.findById(id);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "transaction.service.getById" + id
            );
        }
        if (repositoryResponse.isPresent()) {
            return new APIResponse().withStatus(HttpStatus.OK)
                    .withData(repositoryResponse)
                    .withMessage("transaction.service.getById");
        } else {
            return new APIResponse().withStatus(HttpStatus.NOT_FOUND)
                    .withData(null)
                    .withMessage("transaction.service.getById");
        }
    }

    public APIResponse create(Transaction body) throws BaseException {
        Transaction newTransaction;
        try {
            newTransaction = repository.save(body);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "transaction.service.create" + body.toString()
            );
        }
        return new APIResponse().withStatus(HttpStatus.CREATED)
                .withData(newTransaction)
                .withMessage("transactions.service.create");
    }

    public APIResponse edit(Long id, Transaction body) throws BaseException {
        Transaction newTransaction;
        try {
            newTransaction = repository.getReferenceById(id);
            newTransaction.setStatus(body.getStatus());
            newTransaction.setBalance(body.getBalance());
            newTransaction.setUpdated_at(LocalTime.now());

            newTransaction = repository.save(newTransaction);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "transaction.service.edit" + body.toString()
            );
        }
        return new APIResponse().withStatus(HttpStatus.ACCEPTED)
                .withData(newTransaction)
                .withMessage("transactions.service.edit");
    }

    public APIResponse remove(Long id) throws BaseException {
        Transaction newTransaction;
        try {
            repository.deleteById(id);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "transaction.service.remove" + id
            );
        }
        return new APIResponse().withStatus(HttpStatus.OK)
                .withData(id)
                .withMessage("transactions.service.remove");
    }
}
