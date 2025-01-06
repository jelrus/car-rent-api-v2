package com.car_rent_api.exception;

public class ExistenceException extends RuntimeException {

    public ExistenceException(String message) {
        super(message);
    }
}