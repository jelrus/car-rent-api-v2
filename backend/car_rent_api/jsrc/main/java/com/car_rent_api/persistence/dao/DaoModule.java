package com.car_rent_api.persistence.dao;

import com.car_rent_api.config.Resources;
import com.car_rent_api.persistence.dao.components.*;
import com.car_rent_api.persistence.dao.components.impl.*;
import dagger.Module;
import dagger.Provides;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.PasswordPolicyType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UpdateUserPoolRequest;
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
        CognitoIdentityProviderClient cognitoIpc = CognitoIdentityProviderClient.builder()
                .region(Region.of(Resources.REGION))
                .build();

        PasswordPolicyType passwordPolicyType = PasswordPolicyType.builder()
                .requireUppercase(true)
                .requireLowercase(true)
                .requireNumbers(true)
                .requireSymbols(false)
                .temporaryPasswordValidityDays(7)
                .minimumLength(8)
                .build();

        UpdateUserPoolRequest updateUserPoolRequest = UpdateUserPoolRequest.builder()
                .userPoolId(Resources.COGNITO_ID)
                .policies(p -> p.passwordPolicy(passwordPolicyType))
                .build();

        cognitoIpc.updateUserPool(updateUserPoolRequest);
        return cognitoIpc;
    }

    @Singleton
    @Provides
    AuthDao provideAuthDao(CognitoIdentityProviderClient cognitoClient) {
        return new AuthDaoImpl(cognitoClient);
    }

    @Singleton
    @Provides
    UserDao provideUserDao(DynamoDbEnhancedClient dbClient) {
        return new UserDaoImpl(dbClient);
    }

    @Singleton
    @Provides
    AboutUsDao provideAboutUsDao(DynamoDbEnhancedClient dbClient) {
        return new AboutUsDaoImpl(dbClient);
    }

    @Singleton
    @Provides
    FaqDao provideFaqDao(DynamoDbEnhancedClient dbClient) {
        return new FaqDaoImpl(dbClient);
    }

    @Singleton
    @Provides
    LocationDao provideLocationDao(DynamoDbEnhancedClient dbClient) {
        return new LocationDaoImpl(dbClient);
    }

    @Singleton
    @Provides
    CarDao provideCarDao(DynamoDbEnhancedClient dbClient) {
        return new CarDaoImpl(dbClient);
    }

    @Singleton
    @Provides
    FeedbackDao provideFeedbackDao(DynamoDbEnhancedClient dbClient) {
        return new FeedbackDaoImpl(dbClient);
    }

    @Singleton
    @Provides
    BookingDao provideBookingDao(DynamoDbEnhancedClient dbClient) {
        return new BookingDaoImpl(dbClient);
    }
}