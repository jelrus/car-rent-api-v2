package com.backend.handler;

import com.backend.handler.impl.GeneralHandler;
import com.backend.handler.impl.PathNotFoundHandler;
import com.backend.handler.impl.users.PostUsersLoginHandler;
import com.backend.handler.impl.users.PostUsersHandler;
import com.backend.service.AuthService;
import com.backend.utils.services.JsonValidationService;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Map;

/**
 * DaoModule is Dagger2 @Module archetype, provides dependency injections related to handling endpoint requests and
 * responses operations for other application modules.
 */
@Module
public class HandlersModule {

    /**
     * Provides configured EndpointHandler dependency (GeneralHandler) based on singleton @Singleton Dagger2 archetype.
     * This implementation of interface EndpointHandler marked with @Named("general") annotation, which means that
     * this implementation can only be accessed with this name (general) if injection is needed.
     * Created implementation of EndpointHandler is GeneralHandler which serves for most requests and responses
     * processing.
     *
     * @param notFoundHandler {@code EndpointHandler} Endpoint handler dependency for error handling,
     *                                               annotation @Named("pathNotFountHandler") means that it can only be
     *                                               accessed via this name (pathNotFountHandler)
     * @param handlerMap {@code Map<String, EndpointHandler>} map dependency, for method and resource resolution
     * @return {@code EndpointHandler} configured EndpointHandler implementation, GeneralHandler
     */
    @Singleton
    @Provides
    @Named("generalHandler")
    public EndpointHandler provideGeneralHandler(
            @Named("pathNotFountHandler") EndpointHandler notFoundHandler,
            Map<String, EndpointHandler> handlerMap
    ) {
        return new GeneralHandler(notFoundHandler, handlerMap);
    }

    /**
     * Provides configured EndpointHandler dependency (PathNotFoundHandler) based on singleton @Singleton Dagger2
     * archetype.
     * This implementation of interface EndpointHandler marked with @Named("pathNotFountHandler") annotation,
     * which means that this implementation can only be accessed with this name (general) if injection is needed.
     *
     * @param gson {@code Gson} Gson dependency
     * @return {@code EndpointHandler} configured EndpointHandler implementation, PathNotFoundHandler
     */
    @Singleton
    @Provides
    @Named("pathNotFountHandler")
    public EndpointHandler provideErrorHandler(Gson gson) {
        return new PathNotFoundHandler(gson);
    }

    /**
     * Provides configured EndpointHandler dependency (PostUsersHandler) based on singleton @Singleton Dagger2
     * archetype.
     * This implementation of interface EndpointHandler marked with @IntoMap and @StringKey annotations which means
     * that value of the @StringKey annotation will be put into the Map<String, EndpointHandler> handlerMap in
     * GeneralHandler on compile-time.
     * This handler is reached when processing 'POST':'v1/users/' endpoint
     *
     * @param authService {@code AuthService} AuthService dependency
     * @param gson {@code Gson} Gson dependency
     * @param jsonValidationService {@code JsonValidationService} JsonValidationService dependency
     * @return {@code EndpointHandler} configured EndpointHandler implementation, PostUsersHandler
     */
    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/users")
    public EndpointHandler providePostUsersHandler(
            AuthService authService,
            Gson gson,
            JsonValidationService jsonValidationService
    ) {
        return new PostUsersHandler(authService, gson, jsonValidationService);
    }

    /**
     * Provides configured EndpointHandler dependency (PostUsersLoginHandler) based on singleton @Singleton Dagger2
     * archetype.
     * This implementation of interface EndpointHandler marked with @IntoMap and @StringKey annotations which means
     * that value of the @StringKey annotation will be put into the Map<String, EndpointHandler> handlerMap in
     * GeneralHandler on compile-time.
     * This handler is reached when processing 'POST':'v1/users/login' endpoint
     *
     * @param authService {@code AuthService} AuthService dependency
     * @param gson {@code Gson} Gson dependency
     * @param jsonValidationService {@code JsonValidationService} JsonValidationService dependency
     * @return {@code EndpointHandler} configured EndpointHandler implementation, PostUsersLoginHandler
     */
    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/users/login")
    public EndpointHandler provideLoginHandler(
            AuthService authService,
            Gson gson,
            JsonValidationService jsonValidationService
    ) {
        return new PostUsersLoginHandler(authService, gson, jsonValidationService);
    }
}



