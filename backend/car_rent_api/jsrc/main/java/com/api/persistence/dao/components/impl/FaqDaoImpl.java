package com.api.persistence.dao.components.impl;

import com.api.config.Resources;
import com.api.config.TableKeys;
import com.api.persistence.dao.components.FaqDao;
import com.api.persistence.models.entity.FaqStory;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;

public class FaqDaoImpl implements FaqDao {

    private final DynamoDbTable<FaqStory> faqVolume;

    public FaqDaoImpl(DynamoDbEnhancedClient dbClient) {
        this.faqVolume = dbClient.table(Resources.DYNAMO_DATABASE, TableSchema.fromClass(FaqStory.class));
    }

    @Override
    public List<FaqStory> findAll() {
        Key faqStoryKey = Key.builder()
                .partitionValue(TableKeys.FAQ_PK)
                .sortValue(TableKeys.FAQ_SK_PREFIX)
                .build();
        QueryConditional faqCondition = QueryConditional.sortBeginsWith(faqStoryKey);
        return faqVolume.query(faqCondition).stream().map(Page::items).flatMap(List::stream).toList();
    }
}