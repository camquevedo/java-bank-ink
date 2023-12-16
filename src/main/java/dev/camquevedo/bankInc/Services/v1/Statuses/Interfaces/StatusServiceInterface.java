package dev.camquevedo.bankInc.Services.v1.Statuses.Interfaces;

import dev.camquevedo.bankInc.common.APIResponse;
import dev.camquevedo.bankInc.common.BaseException;
import org.springframework.stereotype.Service;

@Service
public interface StatusServiceInterface {

    APIResponse getAll() throws BaseException;
    APIResponse getById(Long id) throws BaseException;
}
