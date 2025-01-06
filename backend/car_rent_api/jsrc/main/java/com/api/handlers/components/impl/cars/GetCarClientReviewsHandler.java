package com.api.handlers.components.impl.cars;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.api.handlers.components.EndpointHandler;
import com.api.persistence.models.dto.feedback.ClientReviewSortedPageableResponse;
import com.api.service.components.FeedbackService;
import com.api.utils.components.GsonPrinter;
import com.api.utils.components.LogPrinter;
import com.api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetCarClientReviewsHandler implements EndpointHandler {

    private final FeedbackService feedbackService;
    private final GsonPrinter gsonPrinter;

    public GetCarClientReviewsHandler(FeedbackService feedbackService, GsonPrinter gsonPrinter) {
        this.feedbackService = feedbackService;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[GetCarClientReviewsHandler] Entering 'GET @ /cars/{carId}/client-review' method");
        Map<String, String> queryParams = event.getQueryStringParameters();
        LogPrinter.warn("[GetCarClientReviewsHandler] Query params acquired {}", gsonPrinter.print().toJson(queryParams));
        String carId = event.getPathParameters().get("carId");
        LogPrinter.warn("[GetCarClientReviewsHandler] Path param acquired {}", gsonPrinter.print().toJson(carId));

        try {
            LogPrinter.info("[GetCarClientReviewsHandler] Request accepted");

            ClientReviewSortedPageableResponse response =
                    feedbackService.findFeedbacksFilteredByCarIdAndSorted(queryParams, carId);
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetCarClientReviewsHandler] Exiting 'GET @ /cars/{carId}/client-review' ({})",
                    jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetCarClientReviewsHandler] Exiting 'GET @ /cars/{carId}/client-reviews' with error {}",
                    jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[GetCarClientReviewsHandler] Exiting 'GET @ /cars/{carId}/client-reviews' method");
            TransactionContext.clear();
        }
    }
}