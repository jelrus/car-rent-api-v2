package com.backend.handler;

import com.backend.handler.impl.GeneralHandler;
import com.backend.handler.impl.ErrorHandler;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Module
public class HandlersModule {

    @Singleton
    @Provides
    @Named("general")
    public EndpointHandler provideGeneralHandler(
            @Named("error") EndpointHandler notFoundHandler,
            @Named("endpointMap") Map<String, EndpointHandler> handlerMap) {
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
    @Named("endpointMap")
    public Map<String, EndpointHandler> provideEndpointMap() {
        return new HashMap<>();
    }
}