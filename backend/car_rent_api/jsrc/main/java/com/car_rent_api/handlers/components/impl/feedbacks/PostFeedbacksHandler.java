package com.car_rent_api.handlers.components.impl.feedbacks;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.config.JsonValidationSchemes;
import com.car_rent_api.handlers.components.EndpointHandler;
import com.car_rent_api.persistence.models.dto.feedback.CreateFeedbackRequest;
import com.car_rent_api.persistence.models.dto.feedback.CreateFeedbacksResponse;
import com.car_rent_api.service.components.FeedbackService;
import com.car_rent_api.utils.components.GsonPrinter;
import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.SchemaValidator;
import com.car_rent_api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class PostFeedbacksHandler implements EndpointHandler {

    private final FeedbackService feedbackService;
    private final SchemaValidator schemaValidator;
    private final GsonPrinter gsonPrinter;

    public PostFeedbacksHandler(FeedbackService feedbackService, SchemaValidator schemaValidator, GsonPrinter gsonPrinter) {
        this.feedbackService = feedbackService;
        this.schemaValidator = schemaValidator;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[PostFeedbacksHandler] Entering 'POST @ /feedbacks' method");

        try {
            CreateFeedbackRequest request = gsonPrinter.print().fromJson(event.getBody(), CreateFeedbackRequest.class);
            String jsonRequest = gsonPrinter.print().toJson(request);
            LogPrinter.info("[PostFeedbacksHandler] Request was acquired {}", jsonRequest);

            schemaValidator.validateModelByJsonSchema(JsonValidationSchemes.CREATE_FEEDBACK_REQUEST, jsonRequest);
            LogPrinter.info("[PostFeedbacksHandler] Request was validated");

            CreateFeedbacksResponse response = feedbackService.create(request);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[PostFeedbacksHandler] Exiting 'POST @ /feedbacks' with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(201).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[PostFeedbacksHandler] Exiting 'POST @ /feedbacks' with error {}", jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[PostFeedbacksHandler] Exiting 'POST @ /feedbacks' method");
            TransactionContext.clear();
        }
    }
}