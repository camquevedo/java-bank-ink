package dev.camquevedo.bankInc.common;

import org.springframework.http.HttpStatus;

public class BaseException extends Exception {
    private HttpStatus status;
    private String message;
    private String context;

    public BaseException(HttpStatus status, String message, String context) {
        this.status = status;
        this.message = message;
        this.context = context;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
