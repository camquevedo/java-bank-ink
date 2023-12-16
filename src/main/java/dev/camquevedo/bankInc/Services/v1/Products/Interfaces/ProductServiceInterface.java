package dev.camquevedo.bankInc.Services.v1.Products.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Product;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.net.http.HttpResponse;
import java.util.List;

@Repository
public interface ProductServiceInterface {

    //public HttpResponse<Object> getAll();
    public APIResponse getAll() throws BaseException;
    public APIResponse getById(Long id) throws BaseException;
    public APIResponse create(Product body) throws BaseException;
    public APIResponse edit(Long id, Product body) throws BaseException;
    public APIResponse remove(Long id) throws BaseException;
}
