package com.backend.models.table;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Faq {

    private String id;
    private String question;
    private String answer;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("Id")
    public String getId() {
        return id;
    }

    @DynamoDbAttribute("question")
    public String getQuestion() {
        return question;
    }

    @DynamoDbAttribute("answer")
    public String getAnswer() {
        return answer;
    }

    public Faq(String answer, String question) {
        this.answer = answer;
        this.question = question;
    }

    public Faq(String answer, String id, String question) {
        this.answer = answer;
        this.id = id;
        this.question = question;
    }

    public Faq() {
    }
}