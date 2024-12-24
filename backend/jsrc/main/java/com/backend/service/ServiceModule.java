package com.backend.service;

import com.backend.dao.AuthDao;
import com.backend.dao.BookingDao;
import com.backend.dao.CarDao;
import com.backend.dao.UserDao;
import com.backend.service.impl.AuthServiceImpl;
import com.backend.service.impl.BookingServiceImpl;
import com.backend.service.impl.CarServiceImpl;
import com.backend.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;


@Module
public class ServiceModule {

    @Singleton
    @Provides
    AuthService provideAuthService(AuthDao authDao, UserDao userDao, Gson gson) {
        return new AuthServiceImpl(authDao, userDao, gson);
    }

    @Singleton
    @Provides
    UserService provideUserService(UserDao userDao, Gson gson) {
        return new UserServiceImpl(userDao, gson);
    }

    @Singleton
    @Provides
    BookingService provideBookingService(BookingDao bookingDao, UserService userService, Gson gson) {
        return new BookingServiceImpl(bookingDao, userService, gson);
    }

    @Singleton
    @Provides
    CarService provideCarService(CarDao carDao, Gson gson) {
        return new CarServiceImpl(carDao, gson);
    }


}
