package com.backend.dao.impl;

import com.backend.dao.FaqDao;
import com.backend.models.table.FaqEntity;
import com.backend.utils.components.Envs;
import com.backend.utils.services.LoggerService;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FaqDaoImpl implements FaqDao {

    private final DynamoDbTable<FaqEntity> faqTable;

    public FaqDaoImpl(DynamoDbEnhancedClient dbEnhancedClient) {
        this.faqTable = dbEnhancedClient.table(Envs.FAQ_TABLE, TableSchema.fromClass(FaqEntity.class));
    }

    @Override
    public FaqEntity create(FaqEntity faq) {
        LoggerService.warn("[FaqDao|Create] Entered method with Faq {}", faq.toString());
        LoggerService.warn("[FaqDao|Create] Attempt to create requested faq");
        try {
            PutItemEnhancedRequest<FaqEntity> faqItem = PutItemEnhancedRequest.builder(FaqEntity.class).item(faq).build();
            faqTable.putItem(faqItem);
        } catch (Exception e) {
            LoggerService.warn(e.getMessage());
        }
        LoggerService.info("[FaqDao|Create] Faq has been successfully created");
        LoggerService.warn("[FaqDao|Create] Exiting method");
        return faq;
    }

    @Override
    public List<FaqEntity> findAll() {
        List<FaqEntity> faqs = new ArrayList<>();
        PageIterable<FaqEntity> scan = faqTable.scan();
        List<FaqEntity> list = scan.items().stream().toList();

        LoggerService.warn("Total record faq " + list.size());
        list.forEach(faqs::add);

        if (faqs.isEmpty()) {
            LoggerService.warn("[FaqDao|FindAll] No faqs found");
            FaqEntity faqOne = create(new FaqEntity("42", "The greatest question ever!!"));
            FaqEntity faqTwo = create(new FaqEntity("TwoBeer", "To be or not to be??"));
            LoggerService.info("[FaqDao|FindAll] Faqs created");
            return Arrays.asList(faqOne, faqTwo);
        }
        return faqs;
    }
}