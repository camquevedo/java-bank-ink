package dev.camquevedo.bankInc.Services.v1.Statuses;

import dev.camquevedo.bankInc.Models.v1.Status;
import dev.camquevedo.bankInc.Repositories.v1.Statuses.Interfaces.StatusRepositoryInterface;
import dev.camquevedo.bankInc.Services.v1.Statuses.Interfaces.StatusServiceInterface;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService implements StatusServiceInterface {
    private final StatusRepositoryInterface repository;
    public StatusService(StatusRepositoryInterface repository) {
        this.repository = repository;
    }


    public APIResponse getAll() throws BaseException {
        List<Status> repositoryResponse;
        try {
            repositoryResponse = repository.findAll();
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "status.service.getAll"
            );
        }
        return new APIResponse().withStatus(HttpStatus.OK)
                .withData(repositoryResponse)
                .withMessage("status.service.getAll");
    }

    public APIResponse getById(Long id) throws BaseException {
        Optional<Status> repositoryResponse;
        try {
            repositoryResponse = repository.findById(id);
        } catch (Throwable e) {
            throw new BaseException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(),
                    "status.service.getById" + id
            );
        }
        if (repositoryResponse.isPresent()) {
            return new APIResponse().withStatus(HttpStatus.OK)
                    .withData(repositoryResponse)
                    .withMessage("status.service.getById");
        } else {
            return new APIResponse().withStatus(HttpStatus.NOT_FOUND)
                    .withData(null)
                    .withMessage("status.service.getById");
        }
    }
}
