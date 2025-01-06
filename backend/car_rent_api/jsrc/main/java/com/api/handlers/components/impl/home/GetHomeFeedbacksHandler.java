package com.api.handlers.components.impl.home;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.api.handlers.components.EndpointHandler;
import com.api.persistence.models.dto.feedback.FeedbacksResponse;
import com.api.service.components.FeedbackService;
import com.api.utils.components.GsonPrinter;
import com.api.utils.components.LogPrinter;
import com.api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetHomeFeedbacksHandler implements EndpointHandler {

    private final FeedbackService feedbackService;
    private final GsonPrinter gsonPrinter;

    public GetHomeFeedbacksHandler(FeedbackService feedbackService, GsonPrinter gsonPrinter) {
        this.feedbackService = feedbackService;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[GetHomeFeedbacksHandler] Entering 'GET @ /home/feedbacks' method");

        try {
            LogPrinter.info("[GetHomeFeedbacksHandler] Request accepted");

            FeedbacksResponse response = feedbackService.findFeedbacksSortedByRentalExperience();
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetHomeFeedbacksHandler] Exiting 'GET @ /home/feedbacks' ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetHomeFeedbacksHandler] Exiting 'GET @ /home/feedbacks' with error {}", jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[GetHomeFeedbacksHandler] Exiting 'GET @ /home/feedbacks' method");
            TransactionContext.clear();
        }
    }
}