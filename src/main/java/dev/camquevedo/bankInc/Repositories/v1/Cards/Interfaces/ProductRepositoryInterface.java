package dev.camquevedo.bankInc.Repositories.v1.Cards.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryInterface {
    public List<Product> findAll();

    public Product findById(Integer id);

    public Long save(Product newProduct);

    public Boolean update(Integer id, Object entity);

    public Boolean delete(Integer id);
}
