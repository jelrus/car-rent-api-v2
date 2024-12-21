package com.backend.service;

import com.backend.service.impl.CognitoServiceImpl;
import com.backend.service.impl.UserServiceImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import com.backend.utils.components.Envs;
import dagger.Module;
import dagger.Provides;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;


@Module
public class ServiceModule {

    @Singleton
    @Provides
    UserService provideUserService() {
        return new UserServiceImpl();
    }

    @Singleton
    @Provides
    @Named("faqService")
    FaqService provideFaqService(@Named("faqDao") FaqDao faqDao) {
        return new FaqServiceImpl(faqDao);
    }

    @Singleton
    @Provides
    @Named("popularCarService")
    PopularCarService providePopularCarService(@Named("popularCarDao") PopularCarDao popularCarDao) {
        return new PopularCarServiceImpl(popularCarDao);
    }
    @Singleton
    @Provides
    @Named("locationService")
    LocationService locationService(@Named("locationDao") LocationDao locationDao) {
        return new LocationServiceImpl(locationDao);
    }

}
