package com.backend.handler.impl.home;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.models.dto.request.AboutUsStoryInfo;
import com.backend.handler.EndpointHandler;

public class GetAboutHandler implements EndpointHandler  {
    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200).withBody("about page"+ new AboutUsStoryInfo());    }
}
