package com.api.backend.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.backend.utils.GlobalResponse;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<GlobalResponse<Void>> handleApiException(ApiException e) {
        return ResponseEntity
            .status(e.getStatus())
            .body(new GlobalResponse<>(new Date(), true, e.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse<Void>> handleGlobalException(Exception e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new GlobalResponse<>(new Date(), true, e.getMessage(), null));
    }
} 