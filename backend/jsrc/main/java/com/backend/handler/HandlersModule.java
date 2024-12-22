package com.backend.handler;

import com.backend.handler.impl.GeneralHandler;
import com.backend.handler.impl.booking.GetBookingsByIdHandler;
import com.backend.handler.impl.PathNotFoundHandler;
import com.backend.handler.impl.booking.PostBookingsHandler;
import com.backend.handler.impl.users.PostUsersLoginHandler;
import com.backend.handler.impl.users.PostUsersHandler;
import com.backend.handler.impl.cars.GetCarsBookedDayByCarIdHandler;
import com.backend.service.AuthService;
import com.backend.service.BookingService;
import com.backend.service.CarService;
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
    public EndpointHandler providePostUsersHandler(AuthService authService, Gson gson) {
        return new PostUsersHandler(authService, gson);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/users/login")
    public EndpointHandler provideLoginHandler(AuthService authService, Gson gson) {
        return new PostUsersLoginHandler(gson, authService);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/bookings")
    public EndpointHandler providePostBookings(BookingService bookingService, UserService userService, CarService carService, Gson gson) {
        return new PostBookingsHandler(bookingService, userService, carService, gson);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/bookings/{clientId}")
    public EndpointHandler provideGetBookingById(BookingService bookingService, Gson gson) {
        return new GetBookingsByIdHandler(bookingService, gson);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/cars/{carId}/booked-days")
    public EndpointHandler provideGetCarsBookedDayByCarId(BookingService bookingService, Gson gson) {
        return new GetCarsBookedDayByCarIdHandler(bookingService, gson);
    }
}



