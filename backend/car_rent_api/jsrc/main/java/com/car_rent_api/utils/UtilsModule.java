package com.car_rent_api.utils;

import com.car_rent_api.persistence.dao.components.AuthDao;
import com.car_rent_api.persistence.dao.components.UserDao;
import com.car_rent_api.utils.components.*;
import dagger.Module;
import dagger.Provides;
import software.amazon.awssdk.services.s3.S3Client;

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

    @Singleton
    @Provides
    EndpointHandlerAuthorizer provideEndpointHandlerAuthorizer(UserDao userDao, AuthDao authDao) {
        return new EndpointHandlerAuthorizer(userDao, authDao);
    }

    @Singleton
    @Provides
    ImageUploader provideImageUploader(S3Client s3Client) {
        return new ImageUploader(s3Client);
    }

    @Singleton
    @Provides
    ReportUploader provideReportUploader(S3Client s3Client) {
        return new ReportUploader(s3Client);
    }
}