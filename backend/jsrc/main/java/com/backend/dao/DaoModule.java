package com.backend.dao;

import com.backend.dao.impl.AuthDaoImpl;
import com.backend.dao.impl.BookingDaoImpl;
import com.backend.dao.impl.UserDaoImpl;
import com.backend.utils.properties.Envs;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

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
    BookingDao provideBookingDao(DynamoDbEnhancedClient dbClient, Gson gson) {
        return new BookingDaoImpl(dbClient, gson);
    }
}