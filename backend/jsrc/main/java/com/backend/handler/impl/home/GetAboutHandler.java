package com.backend.handler.impl.home;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.backend.handler.EndpointHandler;
import com.backend.service.AboutService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

public class GetAboutHandler implements EndpointHandler {
    private final Gson gson = new Gson();
    private final AboutService aboutService;

    public GetAboutHandler(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent requestEvent, Context context) {
        LoggerService.info("GetAboutHandler");
        try {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200).withBody(gson.toJson(aboutService.findAll()));

        } catch (Exception exception) {
            LoggerService.error(exception.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(exception.getMessage());
        }
    }
}
