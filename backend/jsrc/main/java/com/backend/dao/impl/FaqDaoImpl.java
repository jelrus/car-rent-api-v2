package com.backend.dao.impl;

import com.backend.dao.FaqDao;
import com.backend.dao.UserDao;
import com.backend.models.table.Faq;
import com.backend.models.table.User;
import com.backend.utils.components.Envs;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;
import org.apache.commons.collections.IteratorUtils;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FaqDaoImpl implements FaqDao {

    private final DynamoDbTable<Faq> faqTable;
    private final Gson gson;

    public FaqDaoImpl(DynamoDbEnhancedClient dbEnhancedClient, Gson gson) {
        this.faqTable = dbEnhancedClient.table(Envs.FAQ_TABLE, TableSchema.fromClass(Faq.class));
        this.gson = gson;
    }

    @Override
    public void create(Faq faq) {
        LoggerService.warn("[FaqDao|Create] Entered method with Faq {}", gson.toJson(faq));
        LoggerService.warn("[FaqDao|Create] Attempt to create requested faq");
        PutItemEnhancedRequest<Faq> faqItem = PutItemEnhancedRequest.builder(Faq.class).item(faq).build();
        faqTable.putItem(faqItem);
        LoggerService.info("[FaqDao|Create] Faq has been successfully created");
        LoggerService.warn("[FaqDao|Create] Exiting method");
    }

    @Override
    public List<Faq> findAll() {
        List<Faq> faqs = new ArrayList<>();
        PageIterable<Faq> scan = faqTable.scan();
        for (Page<Faq> faqPage : scan) {
            faqPage.items().forEach((item)->{
                System.out.println(item.getQuestion());
                System.out.println(item.getAnswer());
                faqs.add(item);
            });
        }
        if(faqs.isEmpty()){
            LoggerService.warn("[FaqDao|FindAll] No faqs found");
            create(new Faq("42","The greatest question ever!!"));
            LoggerService.info("[FaqDao|FindAll] Faq created");
            return findAll();
        }
        return faqs;
    }
}