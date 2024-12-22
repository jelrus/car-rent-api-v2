package com.backend.utils;

import com.backend.mapper.CarMapper;
import com.backend.mapper.CarMapperImpl;
import com.backend.mapper.FeedbackMapper;
import com.backend.mapper.FeedbackMapperImpl;
import com.backend.mapper.LocationMapper;
import com.backend.mapper.LocationMapperImpl;
import com.backend.utils.services.JsonValidationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Map;

/**
 * UtilsModule is Dagger2 @Module archetype, provides dependencies for objects contained in third party services.
 */
@Module
public class UtilsModule {

    /**
     * Provides Gson dependency for mapping objects into or from JSON.
     *
     * @return {@code Gson} configured implementation of Gson
     */
    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .disableHtmlEscaping()
                .create();
    }

    /**
     * Provides JsonValidationService dependency for JSON model validations against predefined JSON schemas.
     *
     * @return {@code JsonValidationService} configured JsonValidationService implementation
     */
    @Singleton
    @Provides
    JsonValidationService provideJsonValidationService() {
        return new JsonValidationService();
    }

    /**
     * Provides Map<String, String> dependency, which contains CORS headers for responses.
     * This implementation of Map<String, String> marked with @Named("cors") annotation, which means that
     * this implementation can only be accessed with this name (cors) if injection is needed.
     *
     * @return {@code Map<String, String>} configured map with set CORS headers
     */
    @Singleton
    @Provides
    @Named("cors")
    Map<String, String> provideCorsHeaders() {
        return Map.of(
                "Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token",
                "Access-Control-Allow-Origin", "*",
                "Access-Control-Allow-Methods", "*",
                "Accept-Version", "*"
        );
    }

    @Singleton
    @Provides
    @Named("carMapper")
    CarMapper provideCarMapper() {
        return new CarMapperImpl();
    }

    @Singleton
    @Provides
    @Named("feedbackMapper")
    FeedbackMapper provideFeedbackMapper() {
        return new FeedbackMapperImpl();
    }
    @Singleton
    @Provides
    @Named("locationMapper")
    LocationMapper provideLocationMapper() {
        return new LocationMapperImpl();
    }
    }