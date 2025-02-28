package com.api.backend.utils.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
} 