package com.backend.handler.impl.home;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.service.FaqService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

public class GetFaqHandler implements EndpointHandler {

    private final Gson gson;
    private final FaqService faqService;

    public GetFaqHandler(FaqService faqService, Gson gson) {
        this.gson = gson;
        this.faqService = faqService;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        LoggerService.info("GetFaqHandler");
        try {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200).withBody(gson.toJson(faqService.findAll()));

        } catch (Exception exception) {
            LoggerService.error(exception.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(exception.getMessage());
        }
    }
}
