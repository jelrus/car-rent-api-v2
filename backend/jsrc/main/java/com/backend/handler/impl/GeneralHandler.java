package com.backend.handler.impl;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.utils.services.LoggerService;

import java.util.Map;

/**
 * GeneralHandler is the implementation of EndpointHandler, serves as the main handler for forwarding requests to
 * appropriate handler by requested method and path.
 */
public class GeneralHandler implements EndpointHandler {

    /**
     * Provides EndpointHandler for handling errors.
     */
    private final EndpointHandler errorHandler;

    /**
     * Provides map for populating available resources with methods
     */
    private final Map<String, EndpointHandler> handlerMap;

    /**
     * Constructs GeneralHandler object with injected EndpointHandler and Map<String, EndpointHandler>.
     *
     * @param errorHandler {@code EndpointHandler} injected Endpoint Handler
     * @param handlerMap {@code Map<String, EndpointHandler>} injected Map
     */
    public GeneralHandler(EndpointHandler errorHandler, Map<String, EndpointHandler> handlerMap) {
        this.errorHandler = errorHandler;
        this.handlerMap = handlerMap;
    }

    /**
     * Handles requests and forwards them to appropriate handler from the method:resource map caught by API Gateway
     * within proxy visibility.
     *
     * @param event {@code APIGatewayProxyRequestEvent} caught APIGatewayProxyRequestEvent
     * @param context {@code Context} Lambda executable context
     * @return {@code APIGatewayProxyResponseEvent} response as the result of handling request resource:method endpoint
     */
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        String routeKey = event.getHttpMethod() + ":" + event.getPath();
        LoggerService.info("[GeneralHandler] Trying to resolve resource with method and path {}", routeKey);
        return handlerMap.getOrDefault(routeKey, errorHandler).handle(event, context);
    }
}