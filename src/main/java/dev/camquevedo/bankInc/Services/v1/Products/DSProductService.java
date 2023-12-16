package dev.camquevedo.bankInc.Services.v1.Products;

import dev.camquevedo.bankInc.Models.v1.Product;
import dev.camquevedo.bankInc.Repositories.v1.Cards.Interfaces.ProductRepositoryInterface;
import dev.camquevedo.bankInc.Services.v1.Products.Interfaces.DSProductServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DSProductService implements DSProductServiceInterface {
    protected ProductRepositoryInterface repository;

    public DSProductService(
            ProductRepositoryInterface repository
    ) {
        this.repository = repository;
    }

    public List<Product> findAll() throws Exception {
        try {
            return repository.findAll();
        } catch (Throwable e) {
            //e.getMessage()
            throw new Exception();
        }
    }

    @Override
    public Product findById(Integer id) {
        return null;
    }

    @Override
    public Boolean save(Product body) {
        return null;
    }

    @Override
    public Boolean update(Integer id, Product body) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
