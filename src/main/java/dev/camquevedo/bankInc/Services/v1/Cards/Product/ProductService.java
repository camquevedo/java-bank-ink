package dev.camquevedo.bankInc.Services.v1.Cards.Product;

import dev.camquevedo.bankInc.Models.v1.Product;
import dev.camquevedo.bankInc.Repositories.v1.Cards.Product.Interfaces.ProductRepositoryInterface;
import dev.camquevedo.bankInc.Services.v1.Cards.Product.Interfaces.ProductServiceInterface;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
//            repositoryResponse = repository.findAll();
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

    public APIResponse getById(Long id) throws BaseException {
        Optional<Product> repositoryResponse;
        try {
            repositoryResponse = repository.findById(id);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "product.service.getById" + id
            );
        }
        if (repositoryResponse.isPresent()) {
            return new APIResponse().withStatus(HttpStatus.OK)
                    .withData(repositoryResponse)
                    .withMessage("products.service.getAll");
        } else {
            return new APIResponse().withStatus(HttpStatus.NOT_FOUND)
                    .withData(new Product())
                    .withMessage("products.service.getAll");
        }
    }

    public APIResponse create(Product body) throws BaseException {
        Product newProduct;
        try {
            newProduct = repository.save(body);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "product.service.create" + body.toString()
            );
        }
        return new APIResponse().withStatus(HttpStatus.CREATED)
                .withData(newProduct)
                .withMessage("products.service.create");
    }

    public APIResponse edit(Long id, Product body) throws BaseException {
        Product newProduct;
        try {
            newProduct = repository.getReferenceById(id);
            newProduct.setName(body.getName());
            newProduct.setNumber(body.getNumber());

            newProduct = repository.save(newProduct);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "product.service.edit" + body.toString()
            );
        }
        return new APIResponse().withStatus(HttpStatus.ACCEPTED)
                .withData(newProduct)
                .withMessage("products.service.edit");
    }

    public APIResponse remove(Long id) throws BaseException {
        try {
            repository.deleteById(id);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "product.service.remove" + id
            );
        }
        return new APIResponse().withStatus(HttpStatus.OK)
                .withData(id)
                .withMessage("products.service.remove");
    }
}
