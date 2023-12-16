package dev.camquevedo.bankInc.Services.v1.Cards.Product.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Product;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.stereotype.Service;

@Service
public interface ProductServiceInterface {

    APIResponse getAll() throws BaseException;
    APIResponse getById(Long id) throws BaseException;
    APIResponse create(Product body) throws BaseException;
    APIResponse edit(Long id, Product body) throws BaseException;
    APIResponse remove(Long id) throws BaseException;
}
