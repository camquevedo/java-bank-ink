package dev.camquevedo.bankInc.Services.v1.Transactions.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Transaction;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.stereotype.Service;

@Service
public interface TransactionServiceInterface {

    public APIResponse getAll() throws BaseException;
    public APIResponse getById(Long id) throws BaseException;
    public APIResponse create(Transaction body) throws BaseException;
    public APIResponse edit(Long id, Transaction body) throws BaseException;
    public APIResponse remove(Long id) throws BaseException;
}
