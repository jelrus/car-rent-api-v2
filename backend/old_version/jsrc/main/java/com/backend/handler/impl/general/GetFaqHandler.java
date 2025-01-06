package com.backend.handler.impl.general;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.exception.ContentNotFoundException;
import com.backend.handler.EndpointHandler;
import com.backend.service.components.GeneralContentService;
import com.google.gson.Gson;

import java.util.Map;

public class GetFaqHandler implements EndpointHandler {

    private final Gson gson = new Gson();
    private final GeneralContentService generalContentService;

    public GetFaqHandler(GeneralContentService generalContentService, Gson gson) {
        this.generalContentService = generalContentService;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        try {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody(gson.toJson(generalContentService.findFaqStories()));
        } catch (ContentNotFoundException contentNotFoundException) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(404)
                    .withBody(gson.toJson(Map.of("message", contentNotFoundException.getMessage())));
        }
    }
}