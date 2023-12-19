package dev.camquevedo.bankInc.Controllers.v1.Transactions;

import dev.camquevedo.bankInc.Models.v1.Transactions.Transaction;
import dev.camquevedo.bankInc.Services.v1.Transactions.Interfaces.TransactionServiceInterface;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    private final TransactionServiceInterface service;

    public TransactionController(TransactionServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/transaction")
    public APIResponse getAll() throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.getAll();
        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "transaction.controller.getAll.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "transaction.controller.getAll.listAll"
        );
    }

    @GetMapping("/transaction/{id}")
    public APIResponse getById(@PathVariable Long id) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.getById(id);

        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "transaction.controller.getById.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "transaction.controller.getById.listById"
        );
    }

    @PostMapping("/transaction")
    public APIResponse create(@RequestBody Transaction body) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.create(body);
        if (serviceResponse.getStatus() != HttpStatus.CREATED) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "transaction.controller.create.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "transaction.controller.create.created"
        );
    }

    @PutMapping("/transaction/{id}")
    public APIResponse update(@PathVariable Long id, @RequestBody Transaction body) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.edit(id, body);

        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "transaction.controller.update.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "transaction.controller.update.success"
        );
    }

    @DeleteMapping("/transaction/{id}")
    public APIResponse delete(@PathVariable Long id) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.remove(id);

        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "transaction.controller.delete.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "transaction.controller.delete.removed"
        );
    }
}
