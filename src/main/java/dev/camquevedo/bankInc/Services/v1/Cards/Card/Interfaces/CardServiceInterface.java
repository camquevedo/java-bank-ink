package dev.camquevedo.bankInc.Services.v1.Cards.Card.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Cards.Card;
import dev.camquevedo.bankInc.Models.v1.Transactions.RechargeBalance;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.stereotype.Service;

@Service
public interface CardServiceInterface {

    APIResponse getAll() throws BaseException;
    APIResponse getById(Long id) throws BaseException;
    APIResponse getByNumber(Long id) throws BaseException;

    APIResponse generateCard(Long id) throws BaseException;
    APIResponse create(Card body) throws BaseException;
    APIResponse edit(Long id, Card body) throws BaseException;
    APIResponse remove(Long id) throws BaseException;
    APIResponse updateCardStatus(long number, long status) throws BaseException;

    APIResponse createTransaction(RechargeBalance body, boolean type) throws BaseException;
}
