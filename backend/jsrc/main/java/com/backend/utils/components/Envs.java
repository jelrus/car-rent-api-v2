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
     * Represents Cognito ID of Cognito Identity Provider
     */
    public static final String COGNITO_ID = System.getenv("COGNITO_ID");

    /**
     * Represents Client ID of Cognito Identity Provider
     */
    public static final String CLIENT_ID = System.getenv("CLIENT_ID");

    /**
     * Represents DynamoDB table designed for Faq objects
     */
    public static final String FAQ_TABLE = System.getenv("FAQ_TABLE");

    /**
     * Represents DynamoDB table designed for Car objects
     */
    public static final String CARS_TABLE = System.getenv("CARS_TABLE");

    /**
     * Represents DynamoDB table designed for Location objects
     */
    public static final String LOCATIONS_TABLE = System.getenv("LOCATIONS_TABLE");
}