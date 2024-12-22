package com.backend.handler.impl;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

import java.util.Map;

/**
 * PathNotFoundHandler is the implementation of EndpointHandler, serves as the handler for errors related with
 * non-existing path and method applied to this path.
 */
public class PathNotFoundHandler implements EndpointHandler {

    /**
     * Provides Gson for mapping objects into JSON format.
     */
    private final Gson gson;

    /**
     * Constructs PostUsersLoginHandler object with injected AuthService, Gson and JsonValidationService.
     *
     * @param gson {@code Gson} injected Gson
     */
    public PathNotFoundHandler(Gson gson) {
        this.gson = gson;
    }

    /**
     * Handles requests related with page not found errors (mostly related to incorrect requested path and method),
     * responses with HTTP status code 404 and error message.
     *
     * @param event {@code APIGatewayProxyRequestEvent} caught APIGatewayProxyRequestEvent
     * @param context {@code Context} Lambda executable context
     * @return {@code APIGatewayProxyResponseEvent} response as the result of error handling for non-existing
     * resource:method endpoint
     */
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        String errorMessage = "Requested path " + event.getPath() + " with " + event.getHttpMethod() + " was not found";
        LoggerService.info("[PathNotFoundHandler] {}", errorMessage);
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(404)
                .withBody(gson.toJson(Map.of("message", errorMessage)));
    }
}