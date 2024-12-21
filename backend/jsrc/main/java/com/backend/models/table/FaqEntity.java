package com.backend.models.table;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.UUID;

@DynamoDbBean
public class FaqEntity {

    private String id;
    private String question;
    private String answer;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
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

    public FaqEntity(String answer, String id, String question) {
        this.answer = answer;
        this.id = id;
        this.question = question;
    }

    public FaqEntity(String answer, String question) {
       this.id = UUID.randomUUID().toString();
        this.answer = answer;
        this.question = question;
    }

    public FaqEntity() {
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "FaqEntity{" +
                "question:'" + question + '\'' +
                ", answer:'" + answer + '\'' +
                '}';
    }
}