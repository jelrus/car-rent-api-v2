package com.car_rent_api.persistence.dao.components.impl;

import com.car_rent_api.config.Resources;
import com.car_rent_api.config.TableKeys;
import com.car_rent_api.persistence.dao.components.FeedbackDao;
import com.car_rent_api.persistence.models.entity.Feedback;
import com.car_rent_api.persistence.specification.FeedbackPageRequest;
import com.car_rent_api.persistence.specification.FeedbackPageResponse;
import com.car_rent_api.persistence.specification.PaginationBuilder;
import com.car_rent_api.utils.components.LogPrinter;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.Select;

import java.util.List;

public class FeedbackDaoImpl implements FeedbackDao {

    private final DynamoDbTable<Feedback> feedbackVolume;

    public FeedbackDaoImpl(DynamoDbEnhancedClient dbClient) {
        this.feedbackVolume = dbClient.table(Resources.DYNAMO_DATABASE, TableSchema.fromClass(Feedback.class));
    }

    @Override
    public List<Feedback> findFeedbacksSortedByRentalExperience() {
        LogPrinter.info("[FeedbackDao | Find Feedbacks Sorted By Rental Experience] Entering FeedbackDao " +
                "findFeedbacksSortedByRentalExperience() {}");
        Key feedbackKey = Key.builder().partitionValue(TableKeys.FEEDBACK_PK).build();
        QueryConditional feedbackCondition = QueryConditional.keyEqualTo(feedbackKey);
        QueryEnhancedRequest feedbackRequest = QueryEnhancedRequest.builder()
                .queryConditional(feedbackCondition)
                .select(Select.ALL_ATTRIBUTES)
                .scanIndexForward(false)
                .build();

        LogPrinter.info("[FeedbackDao | Find Feedbacks Sorted By Rental Experience] Querying...");
        return feedbackVolume.index(TableKeys.FEEDBACK_RENTAL_EXPERIENCE_IDX)
                .query(feedbackRequest)
                .stream()
                .map(Page::items)
                .flatMap(List::stream)
                .toList();
    }

    @Override
    public FeedbackPageResponse findFeedbacksPaginatedAndFiltered(FeedbackPageRequest feedbackPageRequest) {
        LogPrinter.info("[FeedbackDao | Find Feedbacks Paginated By Car Id] Entering FeedbackDao " +
                "findFeedbacksPaginatedByCarId() {}");
        Key feedbackKey = Key.builder().partitionValue(TableKeys.FEEDBACK_PK).build();
        QueryConditional feedbackCondition = QueryConditional.keyEqualTo(feedbackKey);
        QueryEnhancedRequest feedbackRequest = QueryEnhancedRequest.builder()
                .queryConditional(feedbackCondition)
                .scanIndexForward(feedbackPageRequest.getDirection())
                .filterExpression(feedbackPageRequest.getFilter())
                .build();

        LogPrinter.info("[FeedbackDao | Find Feedbacks Filtered] Querying...");
        SdkIterable<Page<Feedback>> feedbackPages = feedbackVolume.index(feedbackPageRequest.getSort())
                .query(feedbackRequest);

        LogPrinter.info("[FeedbackDao | Find Feedbacks Filtered] Entering pagination for feedbacks filtering");
        PaginationBuilder<Feedback> feedbackPageBuilder =
                new PaginationBuilder<>(feedbackPageRequest.getPage(), feedbackPageRequest.getSize());
        feedbackPageBuilder.paginate(feedbackPages);

        return FeedbackPageResponse.builder()
                .items(feedbackPageBuilder.getItems())
                .currentPage(feedbackPageBuilder.getPage())
                .totalPages(feedbackPageBuilder.getTotalPages())
                .elementsOnPage(feedbackPageBuilder.getElementsOnPage())
                .totalElements(feedbackPageBuilder.getTotalElements())
                .build();
    }
}