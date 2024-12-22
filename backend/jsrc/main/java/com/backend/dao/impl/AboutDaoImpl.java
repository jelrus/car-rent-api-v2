package com.backend.dao.impl;

import com.backend.dao.AboutDao;
import com.backend.models.table.AboutEntity;
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
import java.util.UUID;

public class AboutDaoImpl implements AboutDao {

    private final DynamoDbTable<AboutEntity> aboutTable;

    public AboutDaoImpl(DynamoDbEnhancedClient dbEnhancedClient) {
        this.aboutTable = dbEnhancedClient.table(Envs.ABOUT_TABLE, TableSchema.fromClass(AboutEntity.class));
    }

    @Override
    public AboutEntity create(AboutEntity about) {
        LoggerService.warn("[AboutDao|Create] Entered method with AboutEntity {}", about.toString());
        LoggerService.warn("[AboutDao|Create] Attempt to create requested aboutEntity");
        try {
            PutItemEnhancedRequest<AboutEntity> faqItem = PutItemEnhancedRequest.builder(AboutEntity.class).item(about).build();
            aboutTable.putItem(faqItem);
        } catch (Exception e) {
            LoggerService.warn(e.getMessage());
        }
        LoggerService.info("[AboutDao|Create] AboutEntity has been successfully created");
        LoggerService.warn("[AboutDao|Create] Exiting method");
        return about;
    }

    @Override
    public List<AboutEntity> findAll() {
        List<AboutEntity> faqs = new ArrayList<>();
        PageIterable<AboutEntity> scan = aboutTable.scan();
        List<AboutEntity> list = scan.items().stream().toList();

        LoggerService.warn("Total record faq " + list.size());
        list.forEach(faqs::add);

        if (faqs.isEmpty()) {
            LoggerService.warn("[AboutDao|FindAll] No AboutUs found");
            AboutEntity aboutEntity = create(new AboutEntity("in car rentals highlights a steadfast commitment to excellence, marked by a track record of trust and satisfaction among thousands of clients worldwide"
            , UUID.randomUUID().toString()
            ,"15"
            , "years"));
            LoggerService.info("[AboutDao|FindAll] AboutUs created");
            return Arrays.asList(aboutEntity);
        }
        return faqs;
    }
}