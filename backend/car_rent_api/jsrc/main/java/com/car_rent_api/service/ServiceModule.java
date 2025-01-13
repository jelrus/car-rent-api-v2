package com.car_rent_api.service;

import com.car_rent_api.persistence.dao.components.*;
import com.car_rent_api.service.components.*;
import com.car_rent_api.service.components.impl.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ServiceModule {

    @Singleton
    @Provides
    AuthService provideAuthService(AuthDao authDao, UserDao userDao) {
        return new AuthServiceImpl(authDao, userDao);
    }

    @Singleton
    @Provides
    AboutUsService provideAboutUsService(AboutUsDao aboutUsDao) {
        return new AboutUsServiceImpl(aboutUsDao);
    }

    @Singleton
    @Provides
    FaqService provideFaqService(FaqDao faqDao) {
        return new FaqServiceImpl(faqDao);
    }

    @Singleton
    @Provides
    LocationService provideLocationService(LocationDao locationDao) {
        return new LocationServiceImpl(locationDao);
    }

    @Singleton
    @Provides
    CarService provideCarService(CarDao carDao, LocationDao locationDao) {
        return new CarServiceImpl(carDao, locationDao);
    }

    @Singleton
    @Provides
    FeedbackService provideFeedbackService(FeedbackDao feedbackDao, UserDao userDao, CarDao carDao,
                                           BookingDao bookingDao) {
        return new FeedbackServiceImpl(feedbackDao, userDao, carDao, bookingDao);
    }

    @Singleton
    @Provides
    BookingService provideBookingService(BookingDao bookingDao, AuthDao authDao, UserDao userDao, CarDao carDao,
                                         LocationDao locationDao) {
        return new BookingServiceImpl(bookingDao, authDao, userDao, carDao, locationDao);
    }
}