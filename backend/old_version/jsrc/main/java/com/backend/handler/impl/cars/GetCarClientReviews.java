package com.backend.handler.impl.cars;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;

public class GetCarClientReviews implements EndpointHandler {

    public GetCarClientReviews() {}

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("OK");
    }
}