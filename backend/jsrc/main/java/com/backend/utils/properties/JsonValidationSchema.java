package com.backend.utils.properties;

/**
 * JsonValidationSchema class is the enumeration, enumerates accessible JSON validation schemas contained in resources.
 */
public enum JsonValidationSchema {

    /**
     * Represents UserSignupRequest JSON schema for validation.
     */
    SIGNUP_REQUEST("user-signup-request.json"),

    /**
     * Represents UserSignupResponse JSON schema for validation.
     */
    SIGNUP_RESPONSE("user-signup-response.json"),

    /**
     * Represents UserSignInRequest JSON schema for validation.
     */
    LOGIN_REQUEST("user-login-request.json"),

    /**
     * Represents UserSignInResponse JSON schema for validation.
     */
    LOGIN_RESPONSE("user-login-response.json");

    /**
     * Represents schema file name in string format.
     */
    private final String schema;

    /**
     * Constructs JsonValidationSchema from specified string schema file name value
     *
     * @param schema {@code String} schema file name string representation
     */
    JsonValidationSchema(String schema) {
        this.schema = schema;
    }

    /**
     * Getter for schema field
     *
     * @return {@code String} accessed schema field
     */
    public String getSchema() {
        return schema;
    }
}