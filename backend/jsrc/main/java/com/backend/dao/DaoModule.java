package com.backend.dao;

import com.backend.dao.components.AuthDao;
import com.backend.dao.components.GeneralContentDao;
import com.backend.dao.components.LocationDao;
import com.backend.dao.components.UserDao;
import com.backend.dao.components.impl.AuthDaoImpl;
import com.backend.dao.components.impl.GeneralContentDaoImpl;
import com.backend.dao.components.impl.LocationDaoImpl;
import com.backend.dao.components.impl.UserDaoImpl;
import com.backend.utils.properties.Envs;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.PasswordPolicyType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UpdateUserPoolRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.inject.Singleton;

/**
 * DaoModule is Dagger2 @Module archetype, provides dependencies related to DAO layer operations for other
 * application modules.
 */
@Module
public class DaoModule {

    /**
     * Provides configured DynamoDbEnhancedClient dependency based on singleton @Singleton Dagger2 archetype.
     *
     * @return {@code DynamoDbEnhancedClient} configured Dynamo DB Client implementation
     */
    @Singleton
    @Provides
    DynamoDbEnhancedClient provideDynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(DynamoDbClient.create()).build();
    }

    /**
     * Provides configured CognitoIdentityProviderClient dependency based on singleton @Singleton Dagger2 archetype.
     * <p>
     * Since CognitoIdentityProviderClient password policy can't be configured directly from AWS Syndicate the next
     * configuration for password policy is used:
     *  - Uppercase characters MANDATORY
     *  - Lowercase characters MANDATORY
     *  - Number characters MANDATORY
     *  - Symbols characters NOT MANDATORY
     *  - Minimum password length is 8 characters
     *  - Temporary password validity lasts for 7 days
     *
     * @return {@code CognitoIdentityProviderClient} configured Cognito Identity Provider Client implementation
     */
    @Singleton
    @Provides
    CognitoIdentityProviderClient provideCognitoIdentityProviderClient() {
        CognitoIdentityProviderClient cognitoIpc = CognitoIdentityProviderClient.builder()
                .region(Region.of(Envs.REGION))
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
                .userPoolId(Envs.COGNITO_ID)
                .policies(p -> p.passwordPolicy(passwordPolicyType))
                .build();
        LoggerService.info("Cognito Identity Provider Client has updated its password policy");

        cognitoIpc.updateUserPool(updateUserPoolRequest);
        return cognitoIpc;
    }

    /**
     * Provides configured AuthDao dependency based on singleton @Singleton Dagger2 archetype.
     *
     * @param cognitoClient {@code CognitoIdentityProviderClient} CognitoIdentityProviderClient dependency
     * @param gson {@code Gson} Gson dependency
     * @return {@code AuthDao} implementation of AuthDao
     */
    @Singleton
    @Provides
    AuthDao provideAuthDao(CognitoIdentityProviderClient cognitoClient, Gson gson) {
        return new AuthDaoImpl(cognitoClient, gson);
    }

    /**
     * Provides configured UserDao dependency based on singleton @Singleton Dagger2 archetype.
     *
     * @param dbClient {@code DynamoDbEnhancedClient} DynamoDbEnhancedClient dependency
     * @param gson {@code Gson} Gson dependency
     * @return {@code UserDao} implementation of UserDao
     */
    @Singleton
    @Provides
    UserDao provideUserDao(DynamoDbEnhancedClient dbClient, Gson gson) {
        return new UserDaoImpl(dbClient, gson);
    }

    /**
     * Provides configured GeneralContentDao dependency based on singleton @Singleton Dagger2 archetype.
     *
     * @param dbClient {@code DynamoDbEnhancedClient} DynamoDbEnhancedClient dependency
     * @param gson {@code Gson} Gson dependency
     * @return {@code UserDao} implementation of GeneralContentDao
     */
    @Singleton
    @Provides
    GeneralContentDao provideGeneralContentDao(DynamoDbEnhancedClient dbClient, Gson gson) {
        return new GeneralContentDaoImpl(dbClient, gson);
    }

    @Singleton
    @Provides
    LocationDao provideLocationDao(DynamoDbEnhancedClient dbClient, Gson gson) {
        return new LocationDaoImpl(dbClient, gson);
    }
}