package com.api.persistence.models.entity;

import com.api.config.TableKeys;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class AboutUsStory {

    private String pkId;
    private String skId;
    private String title;
    private String numericValue;
    private String description;

    public AboutUsStory() {}

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

    @DynamoDbAttribute("ABOUT_US#TITLE")
    public String getTitle() {
        return title;
    }

    @DynamoDbAttribute("ABOUT_US#NUMERIC_VALUE")
    public String getNumericValue() {
        return numericValue;
    }

    @DynamoDbAttribute("ABOUT_US#DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public void setSkId(String skId) {
        this.skId = skId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumericValue(String numericValue) {
        this.numericValue = numericValue;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Builder builder() {
        return new AboutUsStory().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setPkId() {
            AboutUsStory.this.pkId = TableKeys.ABOUT_US_PK;
            return this;
        }

        public Builder setSkId(String id) {
            AboutUsStory.this.skId = TableKeys.ABOUT_US_SK_PREFIX + id;
            return this;
        }

        public Builder setTitle(String title) {
            AboutUsStory.this.title = title;
            return this;
        }

        public Builder setNumericValue(String numericValue) {
            AboutUsStory.this.numericValue = numericValue;
            return this;
        }

        public Builder setDescription(String description) {
            AboutUsStory.this.description = description;
            return this;
        }

        public AboutUsStory build() {
            return AboutUsStory.this;
        }
    }
}