package dev.camquevedo.bankInc.Services.v1.Products;

import dev.camquevedo.bankInc.Models.v1.Product;
import dev.camquevedo.bankInc.Repositories.v1.Cards.Interfaces.ProductRepositoryInterface;
import dev.camquevedo.bankInc.Services.v1.Products.Interfaces.ProductServiceInterface;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {
    private final ProductRepositoryInterface repository;
    public ProductService(ProductRepositoryInterface repository) {
        this.repository = repository;
    }


    public APIResponse getAll() throws BaseException {
        List<Product> repositoryResponse;
        try {
            repositoryResponse = repository.findAll();
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "product.service.getAll"
            );
        }
        return new APIResponse().withStatus(HttpStatus.OK)
                .withData(repositoryResponse)
                .withMessage("products.service.getAll");
    }

    public APIResponse getById(Integer id) throws BaseException {
        Product repositoryResponse;
        try {
            repositoryResponse = repository.findById(id);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "product.service.getById" + id
            );
        }
        return new APIResponse().withStatus(HttpStatus.OK)
                .withData(repositoryResponse)
                .withMessage("products.service.getAll");
    }

    public APIResponse create(Product newProduct) throws BaseException {
        Long productId;
        try {
            productId = repository.save(newProduct);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "product.service.create" + newProduct.toString()
            );
        }
        newProduct.setId(productId);
        return new APIResponse().withStatus(HttpStatus.CREATED)
                .withData(newProduct)
                .withMessage("products.service.getAll");
    }

    public APIResponse edit(Integer id, Product body) throws BaseException {
        return new APIResponse().withStatus(HttpStatus.ACCEPTED)
                .withData(body)
                .withMessage("products.service.edit");
    }

    public APIResponse remove(Integer id) throws BaseException {
        return new APIResponse().withStatus(HttpStatus.OK)
                .withData(id)
                .withMessage("products.service.remove");
    }
}
