package com.api.handlers.components.impl.home;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.api.handlers.components.EndpointHandler;
import com.api.persistence.models.dto.faq.FaqResponse;
import com.api.service.components.FaqService;
import com.api.utils.components.GsonPrinter;
import com.api.utils.components.LogPrinter;
import com.api.utils.components.TransactionContext;

import java.util.Map;
import java.util.UUID;

public class GetHomeFaqHandler implements EndpointHandler {

    private final FaqService faqService;
    private final GsonPrinter gsonPrinter;

    public GetHomeFaqHandler(FaqService faqService, GsonPrinter gsonPrinter) {
        this.faqService = faqService;
        this.gsonPrinter = gsonPrinter;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent event, Context context) {
        TransactionContext.setTransactionId(UUID.randomUUID().toString());
        LogPrinter.warn("[GetHomeFaqHandler] Entering 'GET @ /home/faq' method");

        try {
            LogPrinter.info("[GetHomeFaqHandler] Request accepted");

            FaqResponse response = faqService.findAll();
            String jsonResponse = gsonPrinter.print().toJson(response);
            LogPrinter.info("[GetHomeFaqHandler] Exiting 'GET @ /home/faq' with response ({})", jsonResponse);

            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(jsonResponse);
        } catch (Exception handlerError) {
            String jsonError = gsonPrinter.print().toJson(Map.of("message", handlerError.getMessage()));
            LogPrinter.error("[GetHomeFaqHandler] Exiting 'GET @ /home/faq' with error {}", jsonError);

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody(jsonError);
        } finally {
            LogPrinter.info("[GetHomeFaqHandler] Exiting 'GET @ /home/faq' method");
            TransactionContext.clear();
        }
    }
}