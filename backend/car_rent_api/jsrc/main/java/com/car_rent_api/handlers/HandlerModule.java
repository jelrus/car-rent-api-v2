package com.car_rent_api.handlers;

import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.handlers.components.impl.GeneralHandler;
import com.car_rent_api.handlers.components.impl.PathNotFoundHandler;
import com.car_rent_api.handlers.components.impl.bookings.*;
import com.car_rent_api.handlers.components.impl.cars.GetCarBookedDaysHandler;
import com.car_rent_api.handlers.components.impl.cars.GetCarClientReviewsHandler;
import com.car_rent_api.handlers.components.impl.cars.GetCarHandler;
import com.car_rent_api.handlers.components.impl.cars.GetCarsHandler;
import com.car_rent_api.handlers.components.impl.feedbacks.PostFeedbacksHandler;
import com.car_rent_api.handlers.components.impl.home.*;
import com.car_rent_api.handlers.components.impl.users.PostUsersHandler;
import com.car_rent_api.handlers.components.impl.users.PostUsersLoginHandler;
import com.car_rent_api.service.components.*;
import com.car_rent_api.utils.components.EndpointHandlerAuthorizer;
import com.car_rent_api.utils.components.GsonPrinter;
import com.car_rent_api.utils.components.SchemaValidator;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Map;

@Module
public class HandlerModule {

    @Singleton
    @Provides
    @Named("generalHandler")
    public EndpointHandler provideGeneralHandler(
            @Named("pathNotFountHandler") EndpointHandler notFoundHandler,
            Map<String, EndpointHandler> handlerMap
    ) {
        return new GeneralHandler(notFoundHandler, handlerMap);
    }

    @Singleton
    @Provides
    @Named("pathNotFountHandler")
    public EndpointHandler provideErrorHandler() {
        return new PathNotFoundHandler();
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/users")
    public EndpointHandler providePostUsersHandler(AuthService authService, SchemaValidator schemaValidator,
                                                   GsonPrinter gsonPrinter) {
        return new PostUsersHandler(authService, schemaValidator, gsonPrinter);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/users/login")
    public EndpointHandler provideLoginHandler(AuthService authService, SchemaValidator schemaValidator,
                                               GsonPrinter gsonPrinter) {
        return new PostUsersLoginHandler(authService, schemaValidator, gsonPrinter);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/home/about-us")
    public EndpointHandler provideHomeAboutUsHandler(AboutUsService aboutUsService, GsonPrinter gsonPrinter) {
        return new GetHomeAboutUsHandler(aboutUsService, gsonPrinter);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/home/faq")
    public EndpointHandler provideHomeFaqHandler(FaqService faqService, GsonPrinter gsonPrinter) {
        return new GetHomeFaqHandler(faqService, gsonPrinter);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/home/locations")
    public EndpointHandler provideHomeLocationsHandler(LocationService locationService, GsonPrinter gsonPrinter) {
        return new GetHomeLocationsHandler(locationService, gsonPrinter);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/home/popular-cars")
    public EndpointHandler provideHomePopularCarsHandler(CarService carService, GsonPrinter gsonPrinter) {
        return new GetHomePopularCarsHandler(carService, gsonPrinter);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/home/feedbacks")
    public EndpointHandler provideHomeFeedbacksHandler(FeedbackService feedbackService, GsonPrinter gsonPrinter) {
        return new GetHomeFeedbacksHandler(feedbackService, gsonPrinter);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/cars")
    public EndpointHandler provideCarsHandler(CarService carService, GsonPrinter gsonPrinter) {
        return new GetCarsHandler(carService, gsonPrinter);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/cars/{carId}")
    public EndpointHandler provideCarHandler(CarService carService, GsonPrinter gsonPrinter) {
        return new GetCarHandler(carService, gsonPrinter);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/cars/{carId}/booked-days")
    public EndpointHandler provideCarBookedDaysHandler(CarService carService, GsonPrinter gsonPrinter) {
        return new GetCarBookedDaysHandler(carService, gsonPrinter);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/cars/{carId}/client-review")
    public EndpointHandler provideCarClientReviewsHandler(FeedbackService feedbackService, GsonPrinter gsonPrinter) {
        return new GetCarClientReviewsHandler(feedbackService, gsonPrinter);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/bookings")
    public EndpointHandler provideBookingsHandler(
            BookingService bookingService, SchemaValidator schemaValidator, GsonPrinter gsonPrinter,
            EndpointHandlerAuthorizer authorizer
    ) {
        return new PostBookingsHandler(bookingService, schemaValidator, gsonPrinter, authorizer);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("GET:/v1/bookings/{clientId}")
    public EndpointHandler provideBookingsClientHandler(
            BookingService bookingService, GsonPrinter gsonPrinter, EndpointHandlerAuthorizer authorizer
    ) {
        return new GetBookingsClientHandler(bookingService, gsonPrinter, authorizer);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("PUT:/v1/bookings/{clientId}/{bookingId}/edit")
    public EndpointHandler provideBookingsEditHandler(
            BookingService bookingService, SchemaValidator schemaValidator, GsonPrinter gsonPrinter,
            EndpointHandlerAuthorizer authorizer
    ) {
        return new PutBookingsEditHandler(bookingService, schemaValidator, gsonPrinter, authorizer);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("PUT:/v1/bookings/{clientId}/{bookingId}/cancel")
    public EndpointHandler provideBookingsCancelHandler(
            BookingService bookingService, GsonPrinter gsonPrinter, EndpointHandlerAuthorizer authorizer
    ) {
        return new PutBookingsCancelHandler(bookingService, gsonPrinter, authorizer);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("PUT:/v1/bookings/{clientId}/{bookingId}/start")
    public EndpointHandler provideBookingsServiceStartedHandler(
            BookingService bookingService, GsonPrinter gsonPrinter, EndpointHandlerAuthorizer authorizer
    ) {
        return new PutBookingsStartHandler(bookingService, gsonPrinter, authorizer);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("PUT:/v1/bookings/{clientId}/{bookingId}/provide")
    public EndpointHandler provideBookingsServiceProvidedHandler(
            BookingService bookingService, SchemaValidator schemaValidator, GsonPrinter gsonPrinter,
            EndpointHandlerAuthorizer authorizer
    ) {
        return new PutBookingsProvidedHandler(bookingService, schemaValidator, gsonPrinter, authorizer);
    }

    @Singleton
    @Provides
    @IntoMap
    @StringKey("POST:/v1/feedbacks")
    public EndpointHandler provideCreateFeedbackHandler(
            FeedbackService feedbackService, BookingService bookingService, SchemaValidator schemaValidator,
            GsonPrinter gsonPrinter, EndpointHandlerAuthorizer authorizer
    ) {
        return new PostFeedbacksHandler(feedbackService, bookingService, schemaValidator, gsonPrinter, authorizer);
    }
}