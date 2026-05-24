package com.example.mscourier.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CourierNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handlePaymentNotFound(CourierNotFoundException ex) {
        return new ErrorResponse(ex.getMessage());
    }
}
