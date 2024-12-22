package com.backend.exception;

/**
 * AuthException is the exception class, thrown when auth flow logic have inappropriate state.
 */
public class AuthException extends RuntimeException {

    /**
     * Constructs AuthException exception with specified message.
     *
     * @param message {@code String} specified message
     */
    public AuthException(String message) {
        super(message);
    }
}