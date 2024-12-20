package com.backend.utils.components;

/**
 * Envs is the utility class, provides system environment variables from configuration.
 */
public class Envs {

    /**
     * Represents region, where Lambda function is deployed
     */
    public static final String REGION = System.getenv("REGION");

    /**
     * Represents DynamoDB table designed for User objects
     */
    public static final String USERS_TABLE = System.getenv("USERS_TABLE");

    /**
     * Represents DynamoDB table designed for Home objects
     */
    public static final String HOME_TABLE = System.getenv("HOME_TABLE");

    /**
     * Represents DynamoDB table designed for FAQ objects
     */
    public static final String FAQ_TABLE = System.getenv("FAQ_TABLE");

    /**
     * Represents Cognito ID of Cognito Identity Provider
     */
    public static final String COGNITO_ID = System.getenv("COGNITO_ID");

    /**
     * Represents Client ID of Cognito Identity Provider
     */
    public static final String CLIENT_ID = System.getenv("CLIENT_ID");
}