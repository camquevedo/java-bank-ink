package dev.camquevedo.bankInc.Services.v1.Products.Interfaces;

import dev.camquevedo.bankInc.Models.v1.Product;

import java.util.List;

public interface DSProductServiceInterface {

    public List<Product> findAll() throws Exception;
    public Product findById(Integer id);
    public Boolean save(Product body);
    public Boolean update(Integer id, Product body);
    public Boolean delete(Integer id);
}
