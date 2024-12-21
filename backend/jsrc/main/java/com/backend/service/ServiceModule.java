package com.backend.service;

import com.backend.dao.FaqDao;
import com.backend.dao.PopularCarsDao;
import com.backend.service.impl.FaqServiceImpl;
import com.backend.service.impl.PopularCarsServiceImpl;
import com.backend.service.impl.UserServiceImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;


@Module
public class ServiceModule {

    @Singleton
    @Provides
    UserService provideUserService() {
        return new UserServiceImpl();
    }

    //    @Singleton
//    @Provides
//    @Named("cognitoClient")
//    CognitoIdentityProviderClient provideCognitoIdentityProviderClient() {
//        return CognitoIdentityProviderClient.builder().region(Region.of(Envs.REGION)).build();
//    }
    @Singleton
    @Provides
    @Named("faqService")
    FaqService provideFaqService(@Named("faqDao") FaqDao faqDao) {
        return new FaqServiceImpl(faqDao);
    }

    @Singleton
    @Provides
    @Named("popularCarsService")
    PopularCarsService providePopularCarsService(@Named("popularCarsDao") PopularCarsDao popularCarsDao) {
        return new PopularCarsServiceImpl(popularCarsDao);
    }

//    @Singleton
//    @Provides
//    CognitoService provideCognitoService(@Named("cognitoClient") CognitoIdentityProviderClient cognitoClient) {
//        return new CognitoServiceImpl(cognitoClient);
//    }
}
