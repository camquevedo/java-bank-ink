package dev.camquevedo.bankInc.Controllers.v1.Cards;

import dev.camquevedo.bankInc.Models.v1.Product;
import dev.camquevedo.bankInc.Services.v1.Products.Interfaces.ProductServiceInterface;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final ProductServiceInterface service;

    public ProductController(ProductServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/product")
    public APIResponse getAll() throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.getAll();
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

    @GetMapping("/product/{id}")
    public APIResponse getById(@PathVariable Long id) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.getById(id);

        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "product.controller.getById.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "product.controller.getById.listById"
        );
    }

    @PostMapping("/product")
    public APIResponse create(@RequestBody Product body) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.create(body);
        if (serviceResponse.getStatus() != HttpStatus.CREATED) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "product.controller.create.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "product.controller.create.created"
        );
    }

    @PutMapping("/product/{id}")
    public APIResponse update(@PathVariable Long id, @RequestBody Product body) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.edit(id, body);

        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "product.controller.update.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "product.controller.update.success"
        );
    }

    @DeleteMapping("/product/{id}")
    public APIResponse delete(@PathVariable Long id) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.remove(id);

        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "product.controller.delete.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "product.controller.delete.removed"
        );
    }
}
