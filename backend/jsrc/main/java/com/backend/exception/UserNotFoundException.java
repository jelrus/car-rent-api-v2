package com.backend.exception;

/**
 * UserNotFoundException is the exception class, thrown when result of the operations related with user searching
 * is negative.
 */
public class UserNotFoundException extends RuntimeException{

    /**
     * Constructs UserNotFoundException exception with specified message.
     *
     * @param message {@code String} specified message
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}