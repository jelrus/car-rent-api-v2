package com.backend.models.table;

import com.backend.models.table.types.UserRole;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

/**
 * User is the data class, serves as the medium object for User's database operations.
 * Annotation @DynamoDbBean identifies this class as being a DynamoDb mappable entity.
 */
@DynamoDbBean
public class User {

    /**
     * Represents user id.
     */
    private String userId;

    /**
     * Represents user's first name.
     */
    private String firstName;

    /**
     * Represents user's last name.
     */
    private String lastName;

    /**
     * Represents location of user's profile picture.
     */
    private String imageUrl;

    /**
     * Represents user's role.
     */
    private UserRole role;

    /**
     * Getter for userId field.
     * Annotation @DynamoDbPartitionKey identifies this field as partition key for SupportAgent database entry
     * Annotation @DynamoDbAttribute identifies this field as attribute (field) of the Users database entry
     *
     * @return {@code String} accessed email field
     */
    @DynamoDbPartitionKey
    @DynamoDbAttribute("userId")
    public String getUserId() {
        return userId;
    }

    /**
     * Setter for userId field
     *
     * @param userId {@code String} set userId field
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter for firstName field.
     * Annotation @DynamoDbAttribute identifies this field as attribute (field) of the Users database entry
     *
     * @return {@code String} accessed firstName field
     */
    @DynamoDbAttribute("firstName")
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName field
     *
     * @param firstName {@code String} set firstName field
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastName field.
     * Annotation @DynamoDbAttribute identifies this field as attribute (field) of the Users database entry
     *
     * @return {@code String} accessed lastName field
     */
    @DynamoDbAttribute("lastName")
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastName field
     *
     * @param lastName {@code String} set lastName field
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for imageUrl field.
     * Annotation @DynamoDbAttribute identifies this field as attribute (field) of the Users database entry
     *
     * @return {@code String} accessed imageUrl field
     */
    @DynamoDbAttribute("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Setter for imageUrl field
     *
     * @param imageUrl {@code String} set imageUrl field
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Getter for role field.
     * Annotation @DynamoDbAttribute identifies this field as attribute (field) of the Users database entry
     *
     * @return {@code String} accessed role field
     */
    @DynamoDbAttribute("role")
    public UserRole getRole() {
        return role;
    }

    /**
     * Setter for role field
     *
     * @param role {@code String} set role field
     */
    public void setRole(UserRole role) {
        this.role = role;
    }
}