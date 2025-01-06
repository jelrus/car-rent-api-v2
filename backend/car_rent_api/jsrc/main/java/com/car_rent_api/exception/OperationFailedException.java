package com.car_rent_api.exception;

public class OperationFailedException extends RuntimeException {

    public OperationFailedException(String message) {
        super(message);
    }
}