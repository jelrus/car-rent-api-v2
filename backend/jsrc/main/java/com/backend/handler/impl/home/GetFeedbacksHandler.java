package com.backend.handler.impl.home;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.service.FeedbackService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

public class GetFeedbacksHandler implements EndpointHandler {
    private final Gson gson = new Gson();
    private final FeedbackService feedbackService;

    public GetFeedbacksHandler(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        LoggerService.info("GetFeedbacksHandler");
        try {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200).withBody(gson.toJson(feedbackService.findAll()));

        } catch (Exception exception) {
            LoggerService.error(exception.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(exception.getMessage());
        }
    }
}
