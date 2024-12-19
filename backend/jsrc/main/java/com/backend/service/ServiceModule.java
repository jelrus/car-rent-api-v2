package com.backend.service;

import com.backend.service.impl.CognitoServiceImpl;
import com.backend.service.impl.UserServiceImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import com.backend.utils.components.Envs;
import dagger.Module;
import dagger.Provides;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;


@Module
public class ServiceModule {

    @Singleton
    @Provides
    UserService provideUserService() {
        return new UserServiceImpl();
    }

    @Singleton
    @Provides
    @Named("cognitoClient")
    CognitoIdentityProviderClient provideCognitoIdentityProviderClient() {
        return CognitoIdentityProviderClient.builder().region(Region.of(Envs.REGION)).build();
    }

    @Singleton
    @Provides
    CognitoService provideCognitoService(@Named("cognitoClient") CognitoIdentityProviderClient cognitoClient) {
        return new CognitoServiceImpl(cognitoClient);
    }
}
