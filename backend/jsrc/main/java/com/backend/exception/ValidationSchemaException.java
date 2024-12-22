package com.backend.exception;

/**
 * ValidationSchemaException is the exception class, thrown when source JSON schema and target entity model in JSON
 * formats are not matching.
 */
public class ValidationSchemaException extends RuntimeException {

    /**
     * Constructs ValidationSchemaException exception with specified message.
     *
     * @param message {@code String} specified message
     */
    public ValidationSchemaException(String message) {
        super(message);
    }
}