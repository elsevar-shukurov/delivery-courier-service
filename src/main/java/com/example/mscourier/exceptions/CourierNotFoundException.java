package com.example.mscourier.exceptions;

public class CourierNotFoundException extends RuntimeException {
    public CourierNotFoundException(Long courierId) {
        super("Courier not found with id: " + courierId);
    }
}
