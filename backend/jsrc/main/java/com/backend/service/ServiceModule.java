package com.backend.service;

import com.backend.dao.components.AuthDao;
import com.backend.dao.components.GeneralContentDao;
import com.backend.dao.components.LocationDao;
import com.backend.dao.components.UserDao;
import com.backend.service.components.AuthService;
import com.backend.service.components.GeneralContentService;
import com.backend.service.components.LocationService;
import com.backend.service.components.impl.AuthServiceImpl;
import com.backend.service.components.impl.GeneralContentServiceImpl;
import com.backend.service.components.impl.LocationServiceImpl;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

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

    /**
     * Provides configured AuthService dependency based on singleton @Singleton Dagger2 archetype.
     *
     * @param generalContentDao {@code GeneralContentDao} GeneralContentDao dependency
     * @param gson {@code Gson} Gson dependency
     * @return {@code GeneralContentService} configured implementation of GeneralContentService
     */
    @Singleton
    @Provides
    GeneralContentService provideGeneralContentService(GeneralContentDao generalContentDao, Gson gson) {
        return new GeneralContentServiceImpl(generalContentDao, gson);
    }

    @Singleton
    @Provides
    LocationService provideLocationService(LocationDao locationDao, Gson gson) {
        return new LocationServiceImpl(locationDao, gson);
    }
}