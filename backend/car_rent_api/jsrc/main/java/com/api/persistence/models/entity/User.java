package com.api.persistence.models.entity;

import com.api.config.TableKeys;
import com.api.persistence.models.entity.types.UserRole;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class User {

    private String pkId;
    private String skId;
    private String firstName;
    private String lastName;
    private String username;
    private String imageUrl;
    private UserRole role;

    public User() {}

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

    @DynamoDbAttribute("USER#FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    @DynamoDbAttribute("USER#LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    @DynamoDbAttribute("USER#USERNAME")
    public String getUsername() {
        return username;
    }

    @DynamoDbAttribute("USER#IMAGE_URL")
    public String getImageUrl() {
        return imageUrl;
    }

    @DynamoDbAttribute("USER#ROLE")
    public UserRole getRole() {
        return role;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public void setSkId(String skId) {
        this.skId = skId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public static User.Builder builder() {
        return new User().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder pkId() {
            User.this.pkId = TableKeys.USER_PK;
            return this;
        }

        public Builder skId(String id) {
            User.this.skId = TableKeys.USER_SK_PREFIX + id;
            return this;
        }

        public Builder firstName(String firstName) {
            User.this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }

        public Builder username() {
            User.this.username = User.this.getFirstName() + " " + User.this.getLastName();
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            User.this.imageUrl = imageUrl;
            return this;
        }

        public Builder role(UserRole role) {
            User.this.role = role;
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}