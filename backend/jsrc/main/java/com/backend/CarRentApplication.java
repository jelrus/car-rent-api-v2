package com.backend;

import com.backend.handler.EndpointHandler;
import com.backend.handler.HandlersModule;
import com.backend.service.ServiceModule;
import com.backend.utils.UtilsModule;
import dagger.Component;

import javax.inject.Singleton;
import javax.inject.Named;
import java.util.Map;

@Singleton
@Component(modules = {UtilsModule.class, HandlersModule.class, ServiceModule.class})
public interface CarRentApplication {

    @Named("general")
    EndpointHandler getGeneralApiHandler();

    @Named("cors")
    Map<String, String> getCorsHeaders();
}