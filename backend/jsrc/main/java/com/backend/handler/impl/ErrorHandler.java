package com.backend.handler.impl;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.google.gson.Gson;

import java.util.Map;

public class ErrorHandler implements EndpointHandler {

    private final Gson gson;

    public ErrorHandler(Gson gson) {
        this.gson = gson;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(400)
                .withBody(gson.toJson(Map.of("message", "Bad request")));
    }
}