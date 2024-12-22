package com.backend.dao;

import dagger.Module;
import dagger.Provides;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class DaoModule {

    @Singleton
    @Provides
    DynamoDbEnhancedClient provideDynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(DynamoDbClient.create()).build();
    }

    @Singleton
    @Provides
    CognitoIdentityProviderClient provideCognitoIdentityProviderClient() {
        return CognitoIdentityProviderClient.builder().region(Region.of(Envs.REGION)).build();
    }

    @Singleton
    @Provides
    AuthDao provideAuthDao(CognitoIdentityProviderClient cognitoClient, Gson gson) {
        return new AuthDaoImpl(cognitoClient, gson);
    }

    @Singleton
    @Provides
    UserDao provideUserDao(DynamoDbEnhancedClient dbClient, Gson gson) {
        return new UserDaoImpl(dbClient, gson);
    }

    @Singleton
    @Provides
    @Named("faqDao")
    FaqDao provideFaqDao() {
        return new FaqDaoImpl(provideDynamoDbEnhancedClient());
    }

    @Singleton
    @Provides
    @Named("popularCarDao")
    PopularCarDao providePopularCarDao() {
        return new PopularCarDaoImpl(provideDynamoDbEnhancedClient());
    }

    @Singleton
    @Provides
    @Named("locationDao")
    LocationDao provideLocationDao() {
        return new LocationDaoImpl(provideDynamoDbEnhancedClient());
    }
}