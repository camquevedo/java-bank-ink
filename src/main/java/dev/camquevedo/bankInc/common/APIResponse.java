package dev.camquevedo.bankInc.common;

import org.springframework.http.HttpStatus;

public class APIResponse {

    private HttpStatus status;
    private Object data;
    private String message;

    public APIResponse(HttpStatus status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public APIResponse() {
        this.status = HttpStatus.OK;
        this.data = data;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public APIResponse withStatus(HttpStatus status) {
        this.setStatus(status);
        return this;
    }

    public APIResponse withData(Object data) {
        this.setData(data);
        return this;
    }

    public APIResponse withMessage(String message) {
        this.setMessage(message);
        return this;
    }
}
