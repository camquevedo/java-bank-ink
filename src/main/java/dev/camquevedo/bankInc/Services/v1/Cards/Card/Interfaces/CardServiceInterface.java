package dev.camquevedo.bankInc.Services.v1.Cards.Card.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Card;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.stereotype.Service;

@Service
public interface CardServiceInterface {

    public APIResponse getAll() throws BaseException;
    public APIResponse getById(Long id) throws BaseException;

    public APIResponse generateCard(Long id) throws BaseException;
    public APIResponse create(Card body) throws BaseException;
    public APIResponse edit(Long id, Card body) throws BaseException;
    public APIResponse remove(Long id) throws BaseException;
}
