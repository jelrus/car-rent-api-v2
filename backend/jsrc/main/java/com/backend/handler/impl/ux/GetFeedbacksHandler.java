package com.backend.handler.impl.ux;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.models.dto.response.general.FaqResponse;
import com.backend.handler.EndpointHandler;

public class GetFeedbacksHandler implements EndpointHandler {

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("feedback page" + new FaqResponse());
    }
}