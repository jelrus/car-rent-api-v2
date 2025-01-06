package com.car_rent_api.persistence.dao.components.impl;

import com.car_rent_api.config.Resources;
import com.car_rent_api.config.TableKeys;
import com.car_rent_api.persistence.dao.components.AboutUsDao;
import com.car_rent_api.persistence.models.entity.AboutUsStory;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;

public class AboutUsDaoImpl implements AboutUsDao {

    private final DynamoDbTable<AboutUsStory> aboutUsVolume;

    public AboutUsDaoImpl(DynamoDbEnhancedClient dbClient) {
        this.aboutUsVolume = dbClient.table(Resources.DYNAMO_DATABASE, TableSchema.fromClass(AboutUsStory.class));
    }

    @Override
    public List<AboutUsStory> findAll() {
        Key aboutUsStoryKey = Key.builder()
                .partitionValue(TableKeys.ABOUT_US_PK)
                .sortValue(TableKeys.ABOUT_US_SK_PREFIX)
                .build();
        QueryConditional aboutUsCondition = QueryConditional.sortBeginsWith(aboutUsStoryKey);

        return aboutUsVolume.query(aboutUsCondition).stream().map(Page::items).flatMap(List::stream).toList();
    }
}