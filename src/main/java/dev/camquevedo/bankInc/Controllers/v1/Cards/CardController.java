package dev.camquevedo.bankInc.Controllers.v1.Cards;

import dev.camquevedo.bankInc.Models.v1.Card;
import dev.camquevedo.bankInc.Services.v1.Cards.Card.Interfaces.CardServiceInterface;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardController {
    private final CardServiceInterface service;

    public CardController(CardServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/card")
    public APIResponse getAll() throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.getAll();
        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "card.controller.getAll.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "card.controller.getAll.listAll"
        );
    }

    @GetMapping("/card/{id}")
    public APIResponse getById(@PathVariable Long id) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.getById(id);

        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "card.controller.getById.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "card.controller.getById.listById"
        );
    }

    @PostMapping("/card")
    public APIResponse create(@RequestBody Card body) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.create(body);
        if (serviceResponse.getStatus() != HttpStatus.CREATED) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "card.controller.create.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "card.controller.create.created"
        );
    }

    @PutMapping("/card/{id}")
    public APIResponse update(@PathVariable Long id, @RequestBody Card body) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.edit(id, body);

        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "card.controller.update.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "card.controller.update.success"
        );
    }

    @DeleteMapping("/card/{id}")
    public APIResponse delete(@PathVariable Long id) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.remove(id);

        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "card.controller.delete.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "card.controller.delete.removed"
        );
    }

    @GetMapping("/card/{productId}/number")
    public APIResponse generateCard(@PathVariable Long productId) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.generateCard(productId);

        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "card.controller.generateCard.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "card.controller.generateCard.NumberCardCreated"
        );
    }
}
