package com.backend.utils;

import com.backend.mapper.CarMapper;
import com.backend.mapper.CarMapperImpl;
import com.backend.mapper.FeedbackMapper;
import com.backend.mapper.FeedbackMapperImpl;
import com.backend.mapper.LocationMapper;
import com.backend.mapper.LocationMapperImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Map;

/**
 * UtilsModule is the module, which provides dependencies for objects contained in third party services.
 */
@Module
public class UtilsModule {

    /**
     * Provides Gson object for mapping objects into or from JSON
     *
     * @return {@code Gson} gson object
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
     * Provides Map, which contains CORS headers for responses.
     *
     * @return {@code Map<String, String>} map with set CORS headers
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