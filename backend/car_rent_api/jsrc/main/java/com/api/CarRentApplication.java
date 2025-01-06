package com.api;

import com.api.handlers.HandlerModule;
import com.api.handlers.components.EndpointHandler;
import com.api.persistence.dao.DaoModule;
import com.api.service.ServiceModule;
import com.api.utils.UtilsModule;
import dagger.Component;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
@Component(modules = {DaoModule.class, ServiceModule.class, HandlerModule.class, UtilsModule.class})
public interface CarRentApplication {

    @Named("generalHandler")
    EndpointHandler getGeneralApiHandler();

    @Named("cors")
    Map<String, String> getCorsHeaders();
}