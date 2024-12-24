package com.backend.dao.components.impl;

import com.backend.dao.components.GeneralContentDao;
import com.backend.models.table.AboutUsStory;
import com.backend.models.table.FaqStory;
import com.backend.utils.properties.Envs;
import com.google.gson.Gson;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

/**
 * GeneralContentDaoImpl is the implementation of GeneralContentDao interface, interacts directly with DynamoDB tables
 * related to FaqStory and AboutUs entities, serves as mediator between DynamoDB Client and service layers.
 */
public class GeneralContentDaoImpl implements GeneralContentDao {

    /**
     * Provides DynamoDbTable FaqStories table.
     */
    private final DynamoDbTable<FaqStory> faqStoriesTable;

    /**
     * Provides DynamoDbTable AboutUsStories table.
     */
    private final DynamoDbTable<AboutUsStory> aboutUsTable;

    /**
     * Provides Gson for mapping objects into JSON format.
     */
    private final Gson gson;

    /**
     * Constructs GeneralContentDaoImpl object with injected DynamoDbEnhancedClient and Gson, initiates
     * FaqStories and AboutUsStories table schemas loading from entity classes correspondingly.
     *
     * @param dbClient {@code DynamoDbEnhancedClient} injected DynamoDb Client
     * @param gson {@code Gson} injected Gson
     */
    public GeneralContentDaoImpl(DynamoDbEnhancedClient dbClient, Gson gson) {
        this.faqStoriesTable = dbClient.table(Envs.FAQ_TABLE, TableSchema.fromClass(FaqStory.class));
        this.aboutUsTable = dbClient.table(Envs.ABOUT_US_TABLE, TableSchema.fromClass(AboutUsStory.class));
        this.gson = gson;
    }

    /**
     * Finds all entities of type FaqStory in DynamoDB FaqStories table.
     *
     * @return {@code List<FaqStory>} found FaqStory entities in DynamoDB FaqStories table
     */
    @Override
    public List<FaqStory> findFaqStories() {
        return faqStoriesTable.scan().items().stream().toList();
    }

    /**
     * Finds all entities of type AboutUsStory in DynamoDB AboutUsStories table.
     *
     * @return {@code List<AboutUsStory>} found AboutUsStory entities in DynamoDB AboutUsStories table
     */
    @Override
    public List<AboutUsStory> findAboutUsStories() {
        return aboutUsTable.scan().items().stream().toList();
    }
}