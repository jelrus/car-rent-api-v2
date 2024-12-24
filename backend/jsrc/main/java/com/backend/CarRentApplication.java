package com.backend;

import com.backend.dao.DaoModule;
import com.backend.handler.EndpointHandler;
import com.backend.handler.HandlersModule;
import com.backend.service.ServiceModule;
import com.backend.utils.UtilsModule;
import dagger.Component;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Map;

/**
 * CarRentApplication is Dagger2 @Component archetype, serves as the bridge between the dependency provider
 * (modules annotated with @Module) and the dependency consumer (classes where dependencies are injected).
 */
@Singleton
@Component(modules = {UtilsModule.class, HandlersModule.class, ServiceModule.class, DaoModule.class})
public interface CarRentApplication {

    /**
     * Contract for generating EndpointHandler implementation, GeneralHandler.
     *
     * @return {@code EndpointHandler} configured EndpointHandler implementation, GeneralHandler
     */
    @Named("generalHandler")
    EndpointHandler getGeneralApiHandler();

    /**
     * Contract for generating Map<String, String> implementation for CORS headers.
     *
     * @return {@code Map<String, String>} configured Map<String, String> implementation with set CORS headers
     */
    @Named("cors")
    Map<String, String> getCorsHeaders();
}