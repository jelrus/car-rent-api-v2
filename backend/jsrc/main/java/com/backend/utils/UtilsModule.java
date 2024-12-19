package com.backend.utils;

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
        return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
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
}