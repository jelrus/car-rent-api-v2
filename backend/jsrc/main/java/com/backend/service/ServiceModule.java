package com.backend.service;

import com.backend.dao.AuthDao;
import com.backend.dao.BookingDao;
import com.backend.dao.UserDao;
import com.backend.service.impl.AuthServiceImpl;

import javax.inject.Singleton;

import com.backend.service.impl.BookingServiceImpl;
import com.backend.service.impl.CarServiceImpl;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;


@Module
public class ServiceModule {

    @Singleton
    @Provides
    AuthService provideAuthService(AuthDao authDao, UserDao userDao, Gson gson) {
        return new AuthServiceImpl(authDao, userDao, gson);
    }

    @Singleton
    @Provides
    BookingService provideBookingService(BookingDao bookingDao, Gson gson) {
        return new BookingServiceImpl(bookingDao, gson);
    }

    @Singleton
    @Provides
    CarService provideCarService() {
        return new CarServiceImpl();
    }
}
