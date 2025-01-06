package com.api.handlers.components;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public interface  EndpointHandler {

    APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context);
}