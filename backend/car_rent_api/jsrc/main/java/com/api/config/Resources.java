package com.api.config;

public class Resources {

    public static final String REGION = System.getenv("REGION");
    public static final String DYNAMO_DATABASE = System.getenv("CAR_RENT_APP_VOLUME");
    public static final String COGNITO_ID = System.getenv("COGNITO_ID");
    public static final String CLIENT_ID = System.getenv("CLIENT_ID");
}