package com.backend.handler.impl;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;

import java.util.Map;

public class GeneralHandler implements EndpointHandler {

    private final EndpointHandler errorHandler;
    private final Map<String, EndpointHandler> handlerMap;

    public GeneralHandler(EndpointHandler errorHandler, Map<String, EndpointHandler> handlerMap) {
        this.errorHandler = errorHandler;
        this.handlerMap = handlerMap;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        String routeKey = event.getHttpMethod() + ":" + event.getPath();
        return handlerMap.getOrDefault(routeKey, errorHandler).handle(event, context);
    }
}