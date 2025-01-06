package com.api.handlers.components.impl.home;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.api.handlers.components.EndpointHandler;
import com.api.persistence.models.dto.about_us.AboutUsResponse;
import com.api.service.components.AboutUsService;
import com.api.utils.components.GsonPrinter;
import com.api.utils.components.LogPrinter;
import com.api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetHomeAboutUsHandler implements EndpointHandler {

    private final AboutUsService aboutUsService;
    private final GsonPrinter gsonPrinter;

    public GetHomeAboutUsHandler(AboutUsService aboutUsService, GsonPrinter gsonPrinter) {
        this.aboutUsService = aboutUsService;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[GetHomeAboutUsHandler] Entering 'GET @ /home/about-us' method");

        try {
            LogPrinter.info("[GetHomeAboutUsHandler] Request accepted");

            AboutUsResponse response = aboutUsService.findAll();
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetHomeAboutUsHandler] Exiting 'GET @ /home/about-us' with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetHomeAboutUsHandler] Exiting 'GET @ /home/about-us' with error {}", jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[GetHomeAboutUsHandler] Exiting 'GET @ /home/about-us' method");
            TransactionContext.clear();
        }
    }
}