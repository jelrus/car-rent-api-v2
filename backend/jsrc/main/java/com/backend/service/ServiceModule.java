package com.backend.service;

import com.backend.dao.AuthDao;
import com.backend.dao.UserDao;
import com.backend.service.impl.AuthServiceImpl;

import javax.inject.Singleton;

import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;

/**
 * ServiceModule is Dagger2 @Module archetype, provides dependencies related to service layer operations for
 * other application modules.
 */
@Module
public class ServiceModule {

    /**
     * Provides configured AuthService dependency based on singleton @Singleton Dagger2 archetype.
     *
     * @param authDao {@code AuthDao} AuthDao dependency
     * @param userDao {@code UserDao} UserDao dependency
     * @param gson {@code Gson} Gson dependency
     * @return {@code AuthService} configured implementation of AuthService
     */
    @Singleton
    @Provides
    AuthService provideAuthService(AuthDao authDao, UserDao userDao, Gson gson) {
        return new AuthServiceImpl(authDao, userDao, gson);
    }
}