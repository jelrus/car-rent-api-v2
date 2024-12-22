package com.backend.models.table;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class AboutEntity {

    private String id;
    private String description;
    private String numericValue;
    private String title;


    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }


    @DynamoDbAttribute("description")
    public String getDescription() {
        return description;
    }

    @DynamoDbAttribute("numericValue")
    public String getNumericValue() {
        return numericValue;
    }

    @DynamoDbAttribute("title")
    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumericValue(String numericValue) {
        this.numericValue = numericValue;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AboutEntity(String description, String id, String numericValue, String title) {
        this.description = description;
        this.id = id;
        this.numericValue = numericValue;
        this.title = title;
    }

    public AboutEntity() {
    }
}