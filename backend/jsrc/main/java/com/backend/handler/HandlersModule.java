package com.backend.handler;

import com.backend.handler.impl.ErrorHandler;
import com.backend.handler.impl.GeneralHandler;
import com.backend.handler.impl.PostUsersHandler;
import com.backend.service.CognitoService;
import com.backend.service.UserService;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Map;

@Module
public class HandlersModule {

    @Singleton
    @Provides
    @Named("general")
    public EndpointHandler provideGeneralHandler(
            @Named("error") EndpointHandler notFoundHandler,
            Map<String, EndpointHandler> handlerMap) {
        return new GeneralHandler(notFoundHandler, handlerMap);
    }

    @Singleton
    @Provides
    @Named("error")
    public EndpointHandler provideErrorHandler(Gson gson) {
        return new ErrorHandler(gson);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/users")
    public EndpointHandler providePutUsersHandler(UserService userService, CognitoService cognitoService, Gson gson) {
        return new PostUsersHandler(userService, cognitoService, gson);
    }
}