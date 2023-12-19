package dev.camquevedo.bankInc.Services.v1.Cards.Card;

import dev.camquevedo.bankInc.Models.v1.Cards.Card;
import dev.camquevedo.bankInc.Models.v1.Product;
import dev.camquevedo.bankInc.Models.v1.Transactions.RechargeBalance;
import dev.camquevedo.bankInc.Models.v1.Transactions.Transaction;
import dev.camquevedo.bankInc.Repositories.v1.Cards.Card.Interfaces.Card2RepositoryInterface;
import dev.camquevedo.bankInc.Repositories.v1.Cards.Card.Interfaces.CardRepositoryInterface;
import dev.camquevedo.bankInc.Services.v1.Cards.Card.Interfaces.CardServiceInterface;
import dev.camquevedo.bankInc.Services.v1.Cards.Product.Interfaces.ProductServiceInterface;
import dev.camquevedo.bankInc.Services.v1.Transactions.Interfaces.TransactionServiceInterface;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CardService implements CardServiceInterface {
    private final CardRepositoryInterface repository;
    private final Card2RepositoryInterface repository2;
    private final ProductServiceInterface productService;
    private final TransactionServiceInterface transactionService;
    public CardService(
            CardRepositoryInterface repository,
            Card2RepositoryInterface repository2,
            ProductServiceInterface productService,
            TransactionServiceInterface transactionService
    ) {
        this.repository = repository;
        this.repository2 = repository2;
        this.productService = productService;
        this.transactionService = transactionService;
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

    public APIResponse getByNumber(Long number) throws BaseException {
        List<Card> repositoryResponse;
        try {
            repositoryResponse = repository2.findByNumber(number);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "card.service.getById" + number
            );
        }
        return new APIResponse().withStatus(HttpStatus.OK)
//                .withData(repositoryResponse.get(0))
                .withData(
                        repositoryResponse.size() == 0
                        ? null : repositoryResponse.get(0)
                )
                .withMessage("card.service.getByNumber");
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
            newEntity.setFirstName(body.getFirstName());
            newEntity.setLastName(body.getLastName());
            newEntity.setUpdatedAt(LocalDateTime.now());

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

    public Product findProductById(Long productId) throws BaseException {
        APIResponse productServiceResponse = this.productService.getById(productId);

        if (productServiceResponse.getStatus() != HttpStatus.OK) return new Product();

        Optional<Product> product = (Optional<Product>) productServiceResponse.getData();
        if (product.isPresent()) return product.get();
        throw  new BaseException(
                HttpStatus.NOT_FOUND,
                "entity Not Found",
                "status.service.getAll"
        );
    }

    public APIResponse generateCard(Long productId) throws BaseException {
        Product product = findProductById(productId);

        if (product.getId() == 0)
            return new APIResponse().withStatus(HttpStatus.BAD_REQUEST)
                .withData(product)
                .withMessage("cards.service.generateCard.findProductById.invalidProductId");

        Random random = new Random();
        long randomNumber = Math.abs(random.nextLong());
        String randomString = Long.toString(randomNumber);
        String tenDigitNumber = randomString.substring(0, 10);

        Card newCard = new Card();
        newCard.setStatus(1);
        newCard.setProductId( product.getId() );
        newCard.setNumber( Long.parseLong(product.getNumber() + tenDigitNumber) );
        newCard.setBalance(0);
        newCard.setType(true);
        newCard.setFirstName("Jhon");
        newCard.setLastName("Doe");
        newCard.setExpirationDate(LocalDateTime.now().plusYears(3).toLocalDate());
        newCard.setCreatedAt(LocalDateTime.now());

        APIResponse createdCard = this.create(newCard);

        return new APIResponse().withStatus(HttpStatus.CREATED)
                .withData(createdCard.getData())
                .withMessage("cards.service.generateCard");
    }

    public APIResponse updateCardStatus(long number, long status) throws BaseException
    {
        Card response = (Card) this.getByNumber(number).getData();
        response.status = status;
        response.setUpdatedAt(LocalDateTime.now());

        Card updated = repository.save(response);
        return new APIResponse().withStatus(HttpStatus.OK)
                .withData(response)
                .withMessage("cards.service.updateCardStatus");
    }

    @Override
    public APIResponse createTransaction(RechargeBalance body, boolean type) throws BaseException {
        Card response = (Card) this.getByNumber(body.cardId).getData();
        if (type) response.addBalance(body.balance);
        response.setBalance(0);
        response.setUpdatedAt(LocalDateTime.now());

        Card updated = repository.save(response);
        return new APIResponse().withStatus(HttpStatus.OK)
                .withData(response)
                .withMessage("cards.service.updateCardStatus");
    }

    public APIResponse updateCardBalance(long number, long incomingBalance, boolean type) throws BaseException
    {
        Card response = (Card) this.getByNumber(number).getData();
        if (type) {
            response.addBalance(incomingBalance);
        } else {
            response.removeBalance(incomingBalance);
        }
        response.setUpdatedAt(LocalDateTime.now());

        Card updated = repository.save(response);
        return new APIResponse().withStatus(HttpStatus.OK)
                .withData(response)
                .withMessage("cards.service.updateCardStatus");
    }

    public APIResponse createTransaction(RechargeBalance entity, Boolean type) throws BaseException {
        Card cardEntity = (Card) this.getByNumber(entity.cardId).getData();

        Transaction newTransaction = new Transaction();
        newTransaction.status = 2;
        newTransaction.type = type;
        newTransaction.cardId = cardEntity.id;
        newTransaction.balance = entity.balance;
        APIResponse transactionServiceResponse = transactionService.create(newTransaction);

        return new APIResponse().withStatus(transactionServiceResponse.getStatus())
                .withData(transactionServiceResponse.getData())
                .withMessage("cards.service.createTransaction");
    }
}
