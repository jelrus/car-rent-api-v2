package com.car_rent_api.handlers.components.impl;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.car_rent_api.handlers.components.EndpointHandler;

import java.util.Map;

public class PathNotFoundHandler implements EndpointHandler {

    public PathNotFoundHandler() {}

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        String errorMessage = "Requested path " + event.getPath() + " with " + event.getHttpMethod() + " was not found";

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(404)
                .withBody(Map.of("message", errorMessage).toString());
    }
}