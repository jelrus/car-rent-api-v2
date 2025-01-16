package com.car_rent_api.persistence.dao.components.impl;

import com.car_rent_api.config.Resources;
import com.car_rent_api.config.TableKeys;
import com.car_rent_api.exception.ExistenceException;
import com.car_rent_api.persistence.dao.components.FeedbackDao;
import com.car_rent_api.persistence.models.entity.Feedback;
import com.car_rent_api.persistence.pagination.api.TableRequest;
import com.car_rent_api.persistence.pagination.api.TableResponse;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

public class FeedbackDaoImpl implements FeedbackDao {

    private final DynamoDbTable<Feedback> feedbackVolume;

    public FeedbackDaoImpl(DynamoDbEnhancedClient dbClient) {
        this.feedbackVolume = dbClient.table(Resources.DYNAMO_DATABASE, TableSchema.fromClass(Feedback.class));
    }

    @Override
    public Feedback put(Feedback feedback) {
        PutItemEnhancedRequest<Feedback> feedbackRequest = PutItemEnhancedRequest.builder(Feedback.class).item(feedback)
                .build();
        feedbackVolume.putItem(feedbackRequest);
        return feedback;
    }

    @Override
    public Feedback findByBookingId(String bookingId) {
        Key feedbackKey = Key.builder()
                .partitionValue(TableKeys.FEEDBACK_PK)
                .sortValue(TableKeys.FEEDBACK_SK_PREFIX)
                .build();

        QueryConditional feedbackCondition = QueryConditional.sortBeginsWith(feedbackKey);
        QueryEnhancedRequest feedbackRequest = QueryEnhancedRequest.builder()
                .filterExpression(Expression.builder()
                        .expression("#attr = :attr")
                        .expressionNames(Map.of("#attr", "FEEDBACK#BOOKING_ID"))
                        .expressionValues(Map.of(":attr", AttributeValue.builder().s(bookingId).build()))
                        .build())
                .queryConditional(feedbackCondition)
                .build();

        return feedbackVolume.query(feedbackRequest).items().stream().findFirst()
                .orElseThrow(() -> new ExistenceException("Feedback not found"));
    }

    @Override
    public Double calculateAverageRating(String carId) {
        Key feedbackKey = Key.builder()
                .partitionValue(TableKeys.FEEDBACK_PK)
                .sortValue(TableKeys.FEEDBACK_SK_PREFIX)
                .build();

        QueryConditional feedbackCondition = QueryConditional.sortBeginsWith(feedbackKey);
        QueryEnhancedRequest feedbackRequest = QueryEnhancedRequest.builder()
                .filterExpression(Expression.builder()
                        .expression("#attr = :attr")
                        .expressionNames(Map.of("#attr", "FEEDBACK#CAR_ID"))
                        .expressionValues(Map.of(":attr", AttributeValue.builder().s(carId).build()))
                        .build())
                .queryConditional(feedbackCondition)
                .build();

        List<Double> ratings = feedbackVolume.query(feedbackRequest).items().stream()
                .map(f -> Double.parseDouble(f.getRentalExperience()))
                .toList();

        double average = ratings.stream().reduce(Double::sum).orElse(0D) / ratings.size();

        return BigDecimal.valueOf(average).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public TableResponse<Feedback> findByTableRequestIndexed(TableRequest tableRequest) {
        return TableResponse.<Feedback>builder().init(tableRequest).convertFromPages(findByTableRequest(tableRequest))
                .build();
    }

    @Override
    public TableResponse<Feedback> findByTableRequestIndexedPaginated(TableRequest tableRequest) {
        return TableResponse.<Feedback>builder().init(tableRequest).convertFromPages(findByTableRequest(tableRequest))
                .paginate().build();
    }

    private SdkIterable<Page<Feedback>> findByTableRequest(TableRequest tableRequest) {
        Key carsKey = Key.builder().partitionValue(TableKeys.FEEDBACK_PK).build();
        QueryConditional feedbackCondition = QueryConditional.keyEqualTo(carsKey);

        QueryEnhancedRequest feedbackRequest = QueryEnhancedRequest.builder()
                .queryConditional(feedbackCondition)
                .scanIndexForward(tableRequest.getDirection())
                .filterExpression(tableRequest.getFilter())
                .build();

        return feedbackVolume.index(tableRequest.getSort()).query(feedbackRequest);
    }
}