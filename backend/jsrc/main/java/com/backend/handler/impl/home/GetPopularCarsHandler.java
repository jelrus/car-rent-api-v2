package com.backend.handler.impl.home;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.models.dto.response.PopularCarsResponseBody;
import com.backend.handler.EndpointHandler;

import java.util.logging.Logger;

public class GetPopularCarsHandler implements EndpointHandler  {
Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        System.out.println("inn");
        logger.info("Cognito client config init in region {}");
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200).withBody("popular cars page"+ new PopularCarsResponseBody().toString());    }
}
