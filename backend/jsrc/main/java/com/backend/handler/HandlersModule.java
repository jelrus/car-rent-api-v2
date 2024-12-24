package com.backend.handler;

import com.backend.handler.impl.ErrorHandler;
import com.backend.handler.impl.GeneralHandler;
import com.backend.handler.impl.home.GetAboutHandler;
import com.backend.handler.impl.home.GetFaqHandler;
import com.backend.handler.impl.home.GetFeedbacksHandler;
import com.backend.handler.impl.home.GetLocationsHandler;
import com.backend.handler.impl.home.GetPopularCarsHandler;
import com.backend.service.AboutService;
import com.backend.service.FaqService;
import com.backend.service.FeedbackService;
import com.backend.service.LocationService;
import com.backend.service.PopularCarService;
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

//    @Singleton
//    @Provides
//    @IntoMap
//    @StringKey("POST:/v1/users")
//    public EndpointHandler providePostUsersHandler(UserService userService, CognitoService cognitoService, Gson gson) {
//        return new PostUsersHandler(userService, cognitoService, gson);
//    }

//    private Map<String, EndpointHandler> getStringEndpointHandlerMap() {
//            Map<String, EndpointHandler> map = new HashMap<>();
//
//        if (map.isEmpty()) {
//            return Map.of(
//                    "POST:/v1/users", new UsersHandler(),
//                    "POST:/v1/users/login", new LoginHandler(),
//                    "GET:/v1/home/about-us",new GetAboutHandler(),
//                    "GET:/v1/home/faq",provideFaqHandler(FaqDao),
//                    "GET:/v1/home/feedbacks",new GetFeedbacksHandler(),
//                    "GET:/v1/home/locations",new GetLocationsHandler(),
//                    "GET:/v1/home/popular-cars",new GetPopularCarsHandler()
////                    ,
////                    "GET:/v1/home/about-us",new AboutHandler(),
////                    "GET:/v1/home/faq",new FaqHandler(),
////                    "GET:/v1/home/feedbacks",new FeedbacksHandler(),
////                    "GET:/v1/home/locations",new LocationsHandler(),
////                    "GET:/v1/home/popular-cars",new PopularCars(),
////                    "GET:/v1/cars",new CarsHandler(),
////                    "GET:/v1/cars/{carId}",new CarsByCarIdHandler(),
////                    "GET:/v1/cars/{carId}/booked-days",new CarsBookedDaysHandler(),
////                    "GET:/v1/cars/{carId}/client-review",new CarsReviewHandler(),
////                    "POST:/v1/bookings",new BookingsHandler(),
////                    "GET:/v1/bookings/{clientId}",new  BookingsByClientIdHandler()
//
//            );
//
//        } else {
//            return map;
//        }
//
//    }
//
//    @Singleton
//    @Provides
//    @Named("endpointMap")
//    public Map<String, EndpointHandler> provideEndpointMap() {
//        return getStringEndpointHandlerMap();
//    }
//
//    @Singleton
//    @Provides
//    @IntoMap
//    @StringKey("POST:/v1/users")
//    public EndpointHandler provideUsersHandler() {
//        return new UsersHandler();
//    }
//
//    @Singleton
//    @Provides
//    @IntoMap
//    @StringKey("POST:/v1/users/login")
//    public EndpointHandler provideLoginHandler() {
//        return new LoginHandler();
//    }


    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/home/about-us")
    public EndpointHandler provideAboutHandler(@Named("aboutService") AboutService aboutService) {
        return new GetAboutHandler(aboutService);
    }


    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/home/faq")
    public EndpointHandler provideFaqHandler(@Named("faqService") FaqService faqService) {
        return new GetFaqHandler(faqService);
    }


    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/home/feedbacks")
    public EndpointHandler provideFeedbacksHandler(@Named("feedbackService") FeedbackService feedbackService) {
        return new GetFeedbacksHandler(feedbackService);
    }


    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/home/locations")
    public EndpointHandler provideLocationsHandler(@Named("locationService") LocationService locationService) {
        return new GetLocationsHandler(locationService);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/home/popular-cars")
    public EndpointHandler providePopularCarsHandler(@Named("popularCarService") PopularCarService popularCarService ) {
        return new GetPopularCarsHandler(popularCarService);
    }

}



