package dev.camquevedo.bankInc.Controllers.v1.Cards;

import dev.camquevedo.bankInc.Models.v1.Product;
import dev.camquevedo.bankInc.Services.v1.Products.ProductService;
import dev.camquevedo.bankInc.Services.v1.Products.Interfaces.ProductServiceInterface;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class ProductController {
    private final ProductServiceInterface service;

    public ProductController(ProductServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/product")
    public APIResponse getAll() throws BaseException {
        var serviceResponse = service.getAll();
        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "product.controller.getAll.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "product.controller.getAll.listAll"
        );
    }
    @PostMapping("/product")
    public APIResponse create(@RequestBody Product body) throws BaseException {
        var serviceResponse = service.create(body);
        if (serviceResponse.getStatus() != HttpStatus.CREATED) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "product.controller.getAll.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "product.controller.getAll.listAll"
        );
    }
}
