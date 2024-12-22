package com.backend.dao.impl;

import com.backend.dao.FeedbackDao;
import com.backend.models.table.FeedbackEntity;
import com.backend.utils.components.Envs;
import com.backend.utils.services.LoggerService;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FeedbackDaoImpl implements FeedbackDao {

    private final DynamoDbTable<FeedbackEntity> feedbackTable;

    public FeedbackDaoImpl(DynamoDbEnhancedClient dbEnhancedClient) {
        this.feedbackTable = dbEnhancedClient.table(Envs.FEEDBACK_TABLE, TableSchema.fromClass(FeedbackEntity.class));
    }

    @Override
    public FeedbackEntity create(FeedbackEntity feedback) {
        LoggerService.warn("[FeedbackDao|Create] Entered method with Feedback {}", feedback.toString());
        LoggerService.warn("[FeedbackDao|Create] Attempt to create requested feedback");
        try {
            PutItemEnhancedRequest<FeedbackEntity> feedbackItem = PutItemEnhancedRequest.builder(FeedbackEntity.class).item(feedback).build();
            feedbackTable.putItem(feedbackItem);
        } catch (Exception e) {
            LoggerService.warn(e.getMessage());
        }
        LoggerService.info("[FeedbackDao|Create] Feedback has been successfully created");
        LoggerService.warn("[FeedbackDao|Create] Exiting method");
        return feedback;
    }

    @Override
    public List<FeedbackEntity> findAll() {
        List<FeedbackEntity> feedbacks = new ArrayList<>();
        PageIterable<FeedbackEntity> scan = feedbackTable.scan();
        List<FeedbackEntity> list = scan.items().stream().toList();

        LoggerService.warn("Total record feedback " + list.size());
        list.forEach(feedbacks::add);

        if (feedbacks.isEmpty()) {
            LoggerService.warn("[FeedbackDao|FindAll] No Feedbacks found");
            FeedbackEntity feedback = create(new FeedbackEntity(
                    "John S., New York, USA"
                    , "https://application.s3.eu-central-1.amazonaws.com/img/cars/audi-A6-quattro-2023.jpg"
                    , "Audi A6 Quattro 2023"
                    , "4.5"
                    , "05.10.2024"
                    , UUID.randomUUID().toString()
                    , "I am very satisfied with the service!"
                    , "#2437 (05.10.2024)"
                    , " 4.8"
            ));
            LoggerService.info("[FeedbackDao|FindAll] Feedback created");
            return Arrays.asList(feedback);
        }
        return feedbacks;
    }
}