package dev.camquevedo.bankink.Controllers.v1.Cards;

import dev.camquevedo.bankink.Models.v1.Product;
import dev.camquevedo.bankink.Services.v1.products.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<Product> getAll() {
        return productService.getAll();
    }
}
