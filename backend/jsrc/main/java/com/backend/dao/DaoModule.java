package com.backend.dao;

import com.backend.dao.impl.FaqDaoImpl;
import com.backend.dao.impl.PopularCarsDaoImpl;
import dagger.Module;
import dagger.Provides;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class DaoModule {

    @Singleton
    @Provides
    DynamoDbEnhancedClient provideDynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(DynamoDbClient.create()).build();
    }

    @Singleton
    @Provides
    @Named("faqDao")
    FaqDao provideFaqDao() {
        return new FaqDaoImpl(provideDynamoDbEnhancedClient());
    }

    @Singleton
    @Provides
    @Named("popularCarsDao")
    PopularCarsDao providePopularCarsDao() {
        return new PopularCarsDaoImpl(provideDynamoDbEnhancedClient());
    }
}