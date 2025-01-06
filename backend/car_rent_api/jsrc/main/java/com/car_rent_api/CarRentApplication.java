package com.car_rent_api;

import com.car_rent_api.handlers.HandlerModule;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.dao.DaoModule;
import com.car_rent_api.service.ServiceModule;
import com.car_rent_api.utils.UtilsModule;
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