package com.car_rent_api.persistence.dao.components.impl;

import com.car_rent_api.config.Resources;
import com.car_rent_api.config.TableKeys;
import com.car_rent_api.persistence.dao.components.FeedbackDao;
import com.car_rent_api.persistence.models.entity.Feedback;
import com.car_rent_api.persistence.pagination.api.TableRequest;
import com.car_rent_api.persistence.pagination.api.TableResponse;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

public class FeedbackDaoImpl implements FeedbackDao {

    private final DynamoDbTable<Feedback> feedbackVolume;

    public FeedbackDaoImpl(DynamoDbEnhancedClient dbClient) {
        this.feedbackVolume = dbClient.table(Resources.DYNAMO_DATABASE, TableSchema.fromClass(Feedback.class));
    }

    @Override
    public TableResponse<Feedback> findByTableRequestIndexed(TableRequest tableRequest) {
        Key feedbackKey = Key.builder().partitionValue(TableKeys.FEEDBACK_PK).build();
        QueryConditional feedbackCondition = QueryConditional.keyEqualTo(feedbackKey);
        QueryEnhancedRequest feedbackRequest = QueryEnhancedRequest.builder()
                .queryConditional(feedbackCondition)
                .scanIndexForward(tableRequest.getDirection())
                .filterExpression(tableRequest.getFilter())
                .build();

        SdkIterable<Page<Feedback>> feedbacks = feedbackVolume.index(tableRequest.getSort()).query(feedbackRequest);

        return TableResponse.<Feedback>builder().init(tableRequest).convertFromPages(feedbacks).build();
    }

    @Override
    public TableResponse<Feedback> findByTableRequestIndexedPaginated(TableRequest tableRequest) {
        Key feedbackKey = Key.builder().partitionValue(TableKeys.FEEDBACK_PK).build();
        QueryConditional feedbackCondition = QueryConditional.keyEqualTo(feedbackKey);
        QueryEnhancedRequest feedbackRequest = QueryEnhancedRequest.builder()
                .queryConditional(feedbackCondition)
                .scanIndexForward(tableRequest.getDirection())
                .filterExpression(tableRequest.getFilter())
                .build();

        SdkIterable<Page<Feedback>> feedbackPages = feedbackVolume.index(tableRequest.getSort())
                .query(feedbackRequest);

        return TableResponse.<Feedback>builder().init(tableRequest).convertFromPages(feedbackPages).paginate().build();
    }
}