package dev.camquevedo.bankInc.Services.v1.Cards.Card;

import dev.camquevedo.bankInc.Models.v1.Card;
import dev.camquevedo.bankInc.Models.v1.Product;
import dev.camquevedo.bankInc.Repositories.v1.Cards.Card.Interfaces.CardRepositoryInterface;
import dev.camquevedo.bankInc.Services.v1.Cards.Card.Interfaces.CardServiceInterface;
import dev.camquevedo.bankInc.Services.v1.Cards.Product.Interfaces.ProductServiceInterface;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CardService implements CardServiceInterface {
    private final CardRepositoryInterface repository;
    private final ProductServiceInterface productService;
    public CardService(
            CardRepositoryInterface repository,
            ProductServiceInterface productService
    ) {
        this.repository = repository;
        this.productService = productService;
    }


    public APIResponse getAll() throws BaseException {
        List<Card> repositoryResponse;
        try {
            repositoryResponse = repository.findAll();
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "card.service.getAll"
            );
        }
        return new APIResponse().withStatus(HttpStatus.OK)
                .withData(repositoryResponse)
                .withMessage("cards.service.getAll");
    }

    public APIResponse getById(Long id) throws BaseException {
        Optional<Card> repositoryResponse;
        try {
            repositoryResponse = repository.findById(id);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "card.service.getById" + id
            );
        }
        if (repositoryResponse.isPresent()) {
            return new APIResponse().withStatus(HttpStatus.OK)
                    .withData(repositoryResponse)
                    .withMessage("card.service.getById");
        } else {
            return new APIResponse().withStatus(HttpStatus.NOT_FOUND)
                    .withData(null)
                    .withMessage("card.service.getById");
        }
    }

    public APIResponse create(Card body) throws BaseException {
        Card newCard;
        try {
            newCard = repository.save(body);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "card.service.create" + body.toString()
            );
        }
        return new APIResponse().withStatus(HttpStatus.CREATED)
                .withData(newCard)
                .withMessage("cards.service.create");
    }

    public APIResponse edit(Long id, Card body) throws BaseException {
        Card newEntity;
        try {
            newEntity = repository.getReferenceById(id);
            newEntity.setStatus(body.getStatus());
            newEntity.setNumber(body.getNumber());
            newEntity.setBalance(body.getBalance());
            newEntity.setType(body.isType());
            newEntity.setFirst_name(body.getFirst_name());
            newEntity.setLast_name(body.getLast_name());
            newEntity.setUpdated_at(LocalTime.now());

            newEntity = repository.save(newEntity);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "card.service.edit" + body.toString()
            );
        }
        return new APIResponse().withStatus(HttpStatus.ACCEPTED)
                .withData(newEntity)
                .withMessage("cards.service.edit");
    }

    public APIResponse remove(Long id) throws BaseException {
        try {
            repository.deleteById(id);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "card.service.remove" + id
            );
        }
        return new APIResponse().withStatus(HttpStatus.OK)
                .withData(id)
                .withMessage("cards.service.remove");
    }



    public APIResponse generateCard(Long productId) throws BaseException {
        APIResponse productServiceResponse = this.productService.getById(productId);

        Product product = (Product) productServiceResponse.getData();

         Card newCard = new Card(productId);
         newCard.setNumber(Long.parseLong(product.getNumber() + "" + 1));
         APIResponse createdCard = this.create(newCard);


        return new APIResponse().withStatus(HttpStatus.CREATED)
                .withData(createdCard.getData())
                .withMessage("cards.service.generateCard");
    }
}
