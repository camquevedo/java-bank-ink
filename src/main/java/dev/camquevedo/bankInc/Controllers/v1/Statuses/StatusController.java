package dev.camquevedo.bankInc.Controllers.v1.Statuses;

import dev.camquevedo.bankInc.Services.v1.Statuses.Interfaces.StatusServiceInterface;
import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatusController {
    private final StatusServiceInterface service;
    static private final String routePrefix = "status";

    public StatusController(StatusServiceInterface service) {
        this.service = service;
    }

    @GetMapping(routePrefix)
    public APIResponse getAll() throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.getAll();
        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "status.controller.getAll.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "status.controller.getAll.listAll"
        );
    }

    @GetMapping("status/{id}")
    public APIResponse getById(@PathVariable Long id) throws BaseException {
        APIResponse serviceResponse;
        serviceResponse = service.getById(id);

        if (serviceResponse.getStatus() != HttpStatus.OK) {
            return new APIResponse(
                    serviceResponse.getStatus(),
                    serviceResponse.getData(),
                    "status.controller.getById.notFound"
            );
        }
        return new APIResponse(
                serviceResponse.getStatus(),
                serviceResponse.getData(),
                "status.controller.getById.listById"
        );
    }
}
