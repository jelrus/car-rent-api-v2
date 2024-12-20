package com.backend.dao.impl;

import com.backend.dao.FaqDao;
import com.backend.models.table.Faq;
import com.backend.utils.components.Envs;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FaqDaoImpl implements FaqDao {

    private final DynamoDbTable<Faq> faqTable;
    private final Gson gson = new Gson();

    public FaqDaoImpl(DynamoDbEnhancedClient dbEnhancedClient) {
        this.faqTable = dbEnhancedClient.table(Envs.FAQ_TABLE, TableSchema.fromClass(Faq.class));
    }

    @Override
    public Faq create(Faq faq) {
        LoggerService.warn("[FaqDao|Create] Entered method with Faq {}", gson.toJson(faq));
        LoggerService.warn("[FaqDao|Create] Attempt to create requested faq");
        try {
            PutItemEnhancedRequest<Faq> faqItem = PutItemEnhancedRequest.builder(Faq.class).item(faq).build();
            faqTable.putItem(faqItem);
        } catch (Exception e) {
            LoggerService.warn(e.getMessage());

        }
        LoggerService.info("[FaqDao|Create] Faq has been successfully created");
        LoggerService.warn("[FaqDao|Create] Exiting method");
        return faq;

    }

    @Override
    public List<Faq> findAll() {
        List<Faq> faqs = new ArrayList<>();
        PageIterable<Faq> scan = faqTable.scan();
        List<Faq> list = scan.items().stream().toList();

        LoggerService.warn("Total record faq " + list.size());
        list.forEach(faqs::add);

        if (faqs.isEmpty()) {
            LoggerService.warn("[FaqDao|FindAll] No faqs found");
            Faq faqOne = create(new Faq("42", "The greatest question ever!!"));
            Faq faqTwo = create(new Faq("TwoBeer", "To be or not to be??"));
            LoggerService.info("[FaqDao|FindAll] Faqs created");
            return Arrays.asList(faqOne, faqTwo);
        }
        return faqs;
    }
}