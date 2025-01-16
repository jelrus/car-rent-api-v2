package com.car_rent_api.persistence.models.entity;

import com.car_rent_api.config.TableKeys;
import com.car_rent_api.persistence.models.entity.types.UserRole;
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
    private String postalCode;
    private String country;
    private String city;
    private String street;
    private String phoneNumber;
    private String email;
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

    @DynamoDbAttribute("USER#POSTAL_CODE")
    public String getPostalCode() {
        return postalCode;
    }

    @DynamoDbAttribute("USER#COUNTRY")
    public String getCountry() {
        return country;
    }

    @DynamoDbAttribute("USER#CITY")
    public String getCity() {
        return city;
    }

    @DynamoDbAttribute("USER#STREET")
    public String getStreet() {
        return street;
    }

    @DynamoDbAttribute("USER#PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @DynamoDbAttribute("USER#EMAIL")
    public String getEmail() {
        return email;
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

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Builder toBuilder() {
        return this.new Builder();
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

        public Builder postalCode(String postalCode) {
            User.this.postalCode = postalCode;
            return this;
        }

        public Builder country(String country) {
            User.this.country = country;
            return this;
        }

        public Builder city(String city) {
            User.this.city = city;
            return this;
        }

        public Builder street(String street) {
            User.this.street = street;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            User.this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder email(String email) {
            User.this.email = email;
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