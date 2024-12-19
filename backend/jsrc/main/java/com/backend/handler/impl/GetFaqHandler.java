package com.backend.handler.impl;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.dto.response.FAQResponseBody;
import com.backend.handler.EndpointHandler;

public class GetFaqHandler implements EndpointHandler  {
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200).withBody("faq page" + new FAQResponseBody());    }
}
