package com.car_rent_api.utils;

import com.car_rent_api.utils.components.GsonPrinter;
import com.car_rent_api.utils.components.SchemaValidator;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Map;

@Module
public class UtilsModule {

    @Singleton
    @Provides
    GsonPrinter provideGsonPrinter() {
        return new GsonPrinter();
    }

    @Singleton
    @Provides
    SchemaValidator provideSchemaValidator() {
        return new SchemaValidator();
    }

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