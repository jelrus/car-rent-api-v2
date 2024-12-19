package com.backend.handler.impl;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.dto.AboutUsStoryInfo;
import com.backend.handler.EndpointHandler;

public class GetAboutHandler implements EndpointHandler  {
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200).withBody("about page"+ new AboutUsStoryInfo());    }
}
