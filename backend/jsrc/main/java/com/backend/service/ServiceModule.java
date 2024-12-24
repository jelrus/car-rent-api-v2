package com.backend.service;

import com.backend.dao.AboutDao;
import com.backend.dao.FaqDao;
import com.backend.dao.FeedbackDao;
import com.backend.dao.LocationDao;
import com.backend.dao.PopularCarDao;
import com.backend.mapper.CarMapper;
import com.backend.mapper.FeedbackMapper;
import com.backend.mapper.LocationMapper;
import com.backend.service.impl.AboutServiceImpl;
import com.backend.service.impl.FaqServiceImpl;
import com.backend.service.impl.FeedbackServiceImpl;
import com.backend.service.impl.LocationServiceImpl;
import com.backend.service.impl.PopularCarServiceImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;


@Module
public class ServiceModule {

//    @Singleton
//    @Provides
//    UserService provideUserService() {
//        return new UserServiceImpl();
//    }

    @Singleton
    @Provides
    @Named("faqService")
    FaqService provideFaqService(@Named("faqDao") FaqDao faqDao) {
        return new FaqServiceImpl(faqDao);
    }

    @Singleton
    @Provides
    @Named("popularCarService")
    PopularCarService providePopularCarService(@Named("popularCarDao") PopularCarDao popularCarDao,
                                               @Named("carMapper") CarMapper carMapper) {
        return new PopularCarServiceImpl(popularCarDao, carMapper);
    }

    @Singleton
    @Provides
    @Named("locationService")
    LocationService locationService(@Named("locationDao") LocationDao locationDao,
                                    @Named("locationMapper") LocationMapper locationMapper) {
        return new LocationServiceImpl(locationDao, locationMapper);
    }

    @Singleton
    @Provides
    @Named("feedbackService")
    FeedbackService feedbackService(@Named("feedbackDao") FeedbackDao feedbackDao,
                                    @Named("feedbackMapper") FeedbackMapper feedbackMapper) {
        return new FeedbackServiceImpl(feedbackDao, feedbackMapper);
    }

    @Singleton
    @Provides
    @Named("aboutService")
    AboutService aboutService(@Named("aboutDao") AboutDao aboutDao) {
        return new AboutServiceImpl(aboutDao);
    }

}
