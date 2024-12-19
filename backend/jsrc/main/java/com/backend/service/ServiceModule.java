package com.backend.service;

import com.backend.service.impl.CognitoServiceImpl;
import com.backend.service.impl.UserServiceImpl;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;


@Module
public class ServiceModule {

    @Singleton
    @Provides
    UserService provideUserService() {
        return new UserServiceImpl();
    }

    @Singleton
    @Provides
    CognitoService provideCognitoService() {
        return new CognitoServiceImpl();
    }
}
