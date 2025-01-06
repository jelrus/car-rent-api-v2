package com.backend.utils.properties;

/**
 * Envs class is the data class, serves as container for constants related to AWS Syndicate environment variables
 */
public class Envs {

    /**
     * Represents Lambda function region.
     */
    public static final String REGION = System.getenv("REGION");

    /**
     * Represents DynamoDB Users table.
     */
    public static final String USERS_TABLE = System.getenv("USERS_TABLE");

    /**
     * Represents DynamoDB SupportAgents table.
     */
    public static final String SUPPORT_AGENTS_TABLE = System.getenv("SUPPORT_AGENTS_TABLE");

    public static final String FAQ_TABLE = System.getenv("FAQ_TABLE");

    public static final String ABOUT_US_TABLE = System.getenv("ABOUT_US_TABLE");

    public static final String REVIEWS_TABLE = System.getenv("REVIEWS_TABLE");

    public static final String LOCATIONS_TABLE = System.getenv("LOCATIONS_TABLE");

    public static final String CARS_TABLE = System.getenv("CARS_TABLE");

    /**
     * Represents Cognito IDP Pool ID.
     */
    public static final String COGNITO_ID = System.getenv("COGNITO_ID");

    /**
     * Represents Cognito IDP Client ID.
     */
    public static final String CLIENT_ID = System.getenv("CLIENT_ID");
}