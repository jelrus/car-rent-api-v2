package com.backend.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

/**
 * EndpointHandler is the interface, provides contracts for handling requested APIGatewayProxyRequestEvent events
 * for end points.
 */
public interface EndpointHandler {

    /**
     * Contract for handling request events for method:path endpoint
     *
     * @param event {@code APIGatewayProxyRequestEvent} caught APIGatewayProxyRequestEvent
     * @param context {@code Context} Lambda executable context
     * @return {@code APIGatewayProxyResponseEvent} response as the result of handling request for endpoint
     */
    APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context);
}