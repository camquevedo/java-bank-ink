package dev.camquevedo.bankink.Services.v1.products;

import dev.camquevedo.bankink.Models.v1.Product;
import dev.camquevedo.bankink.Repositories.v1.cards.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.getAll();
    }
}
