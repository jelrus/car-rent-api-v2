package com.backend.handler;

import com.backend.handler.impl.*;
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
            @Named("pathNotFound") EndpointHandler pathNotFoundHandler,
            Map<String, EndpointHandler> handlerMap) {
        return new GeneralHandler(pathNotFoundHandler, handlerMap);
    }

    @Singleton
    @Provides
    @Named("pathNotFound")
    public EndpointHandler provideResourceNotFoundHandler(Gson gson) {
        return new PathNotFoundHandler(gson);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/users")
    public EndpointHandler provideSignupHandler(Gson gson) {
        return new SignupHandler(gson);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/users/login")
    public EndpointHandler provideLoginHandler(Gson gson) {
        return new LoginHandler(gson);
    }
}