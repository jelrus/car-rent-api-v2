package com.backend.exception;

public class CognitoException extends RuntimeException {

    public CognitoException(String message) {
        super(message);
    }
}