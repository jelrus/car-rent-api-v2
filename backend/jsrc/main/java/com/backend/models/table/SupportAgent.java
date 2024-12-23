package com.backend.models.table;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

/**
 * SupportAgent is the data class, serves as the medium object for Support Agent's database operations.
 * Annotation @DynamoDbBean identifies this class as being a DynamoDb mappable entity.
 */
@DynamoDbBean
public class SupportAgent {

    /**
     * Represents Support Agent's email.
     */
    private String email;

    /**
     * Getter for email field.
     * Annotation @DynamoDbPartitionKey identifies this field as partition key for SupportAgents database entry
     * Annotation @DynamoDbAttribute identifies this field as attribute (field) of the SupportAgents database entry
     *
     * @return {@code String} accessed email field
     */
    @DynamoDbPartitionKey
    @DynamoDbAttribute("email")
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email field
     *
     * @param email {@code String} set email field
     */
    public void setEmail(String email) {
        this.email = email;
    }
}