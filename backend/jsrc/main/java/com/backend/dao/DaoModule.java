package com.backend.dao;

import dagger.Module;
import dagger.Provides;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.inject.Singleton;

@Module
public class DaoModule {

    @Singleton
    @Provides
    DynamoDbEnhancedClient provideDynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(DynamoDbClient.create()).build();
    }
}