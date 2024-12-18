package com.backend.handler.impl;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.google.gson.Gson;

import java.util.Map;

public class PathNotFoundHandler implements EndpointHandler {

    private final Gson gson;

    public PathNotFoundHandler(Gson gson) {
        this.gson = gson;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(404)
                .withBody(gson.toJson(Map.of("message", "Requested path " + event.getPath() +
                        " with " + event.getHttpMethod() + " was not found")));
    }
}