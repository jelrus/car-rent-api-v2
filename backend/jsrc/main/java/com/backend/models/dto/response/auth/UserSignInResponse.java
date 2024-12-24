package com.backend.models.dto.response.auth;

import com.google.gson.annotations.Expose;

/**
 * UserSignInResponse is the data class, serves as the medium object for output in User's sign up request operation.
 */
public class UserSignInResponse {

    /**
     * Represents user's access token.
     */
    @Expose
    private String accessToken;

    /**
     * Represents user's role.
     */
    @Expose
    private String role;

    /**
     * Represents user's id.
     */
    @Expose
    private String userId;

    /**
     * Represents location of user's profile picture.
     */
    @Expose
    private String userImageUrl;

    /**
     * Represents user's username.
     */
    @Expose
    private String username;

    /**
     * Getter for accessToken field
     *
     * @return {@code String} accessed accessToken field
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Setter for accessToken field
     *
     * @param accessToken {@code String} set accessToken field
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Getter for role field
     *
     * @return {@code String} accessed role field
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter for role field
     *
     * @param role {@code String} set role field
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Getter for userId field
     *
     * @return {@code String} accessed userId field
     */
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
     * Getter for userImageUrl field
     *
     * @return {@code String} accessed userImageUrl field
     */
    public String getUserImageUrl() {
        return userImageUrl;
    }

    /**
     * Setter for userImageUrl field
     *
     * @param userImageUrl {@code String} set userImageUrl field
     */
    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    /**
     * Getter for username field
     *
     * @return {@code String} accessed username field
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username field
     *
     * @param username {@code String} set username field
     */
    public void setUsername(String username) {
        this.username = username;
    }
}