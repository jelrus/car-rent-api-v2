package com.api.persistence.models.entity;

import com.api.config.TableKeys;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class FaqStory {

    private String pkId;
    private String skId;
    private String question;
    private String answer;

    public FaqStory() {}

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK_ID")
    public String getPkId() {
        return pkId;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SK_ID")
    public String getSkId() {
        return skId;
    }

    @DynamoDbAttribute("FAQ#QUESTION")
    public String getQuestion() {
        return question;
    }

    @DynamoDbAttribute("FAQ#ANSWER")
    public String getAnswer() {
        return answer;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public void setSkId(String skId) {
        this.skId = skId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static Builder builder() {
        return new FaqStory().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder pkId() {
            FaqStory.this.pkId = TableKeys.FAQ_PK;
            return this;
        }

        public Builder skId(String id) {
            FaqStory.this.skId = TableKeys.FAQ_SK_PREFIX + id;
            return this;
        }

        public Builder question(String question) {
            FaqStory.this.question = question;
            return this;
        }

        public Builder answer(String answer) {
            FaqStory.this.answer = answer;
            return this;
        }

        public FaqStory build() {
            return FaqStory.this;
        }
    }
}