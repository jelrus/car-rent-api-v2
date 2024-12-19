package com.backend.handler;

import com.backend.handler.impl.ErrorHandler;
import com.backend.handler.impl.GeneralHandler;
import com.backend.handler.impl.LoginHandler;
import com.backend.handler.impl.PostUsersHandler;
import com.backend.handler.impl.UsersHandler;
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
        return new ErrorHandler();
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/users")
    public EndpointHandler providePostUsersHandler(UserService userService, CognitoService cognitoService, Gson gson) {
        return new PostUsersHandler(userService, cognitoService, gson);
    }

    private Map<String, EndpointHandler> getStringEndpointHandlerMap() {
        if (map.isEmpty()) {
            return Map.of(
                    "POST:/v1/users", new UsersHandler()
//                    "POST:/v1/users/login", new LoginHandler()
//                    ,
//                    "GET:/v1/home/about-us",new AboutHandler(),
//                    "GET:/v1/home/faq",new FaqHandler(),
//                    "GET:/v1/home/feedbacks",new FeedbacksHandler(),
//                    "GET:/v1/home/locations",new LocationsHandler(),
//                    "GET:/v1/home/popular-cars",new PopularCars(),
//                    "GET:/v1/cars",new CarsHandler(),
//                    "GET:/v1/cars/{carId}",new CarsByCarIdHandler(),
//                    "GET:/v1/cars/{carId}/booked-days",new CarsBookedDaysHandler(),
//                    "GET:/v1/cars/{carId}/client-review",new CarsReviewHandler(),
//                    "POST:/v1/bookings",new BookingsHandler(),
//                    "GET:/v1/bookings/{clientId}",new  BookingsByClientIdHandler()

            );

        } else {
            return map;
        }

    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/users/login")
    public EndpointHandler provideLoginHandler(UserService userService, CognitoService cognitoService, Gson gson) {
        return new LoginHandler(gson, cognitoService, userService);
    }


}



