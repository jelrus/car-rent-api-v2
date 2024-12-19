package com.backend.handler;

import com.backend.handler.impl.*;
import com.backend.handler.impl.users.PostHandler;
import com.backend.handler.impl.users.PostLoginHandler;
import com.backend.service.CognitoService;
import com.backend.service.UserService;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Module
public class HandlersModule {
    private final Map<String, EndpointHandler> map = new HashMap<>();

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
        return new PathNotFoundHandler(gson);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/users")
    public EndpointHandler provideSignupHandler(UserService userService, CognitoService cognitoService, Gson gson) {
        return new PostHandler(userService, cognitoService, gson);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/users/login")
    public EndpointHandler provideLoginHandler() {
        return new PostLoginHandler();
    }
}



