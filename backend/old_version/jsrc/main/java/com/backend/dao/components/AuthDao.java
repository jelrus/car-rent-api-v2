package com.backend.dao.components;

import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminGetUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserType;

/**
 * AuthDao is the interface, provides contracts for interaction with Cognito IDP Client.
 */
public interface AuthDao {

    /**
     * Contract for creating requested user on behalf of admin by email and password.
     *
     * @param email {@code String} requested email
     * @param password {@code String} requested password
     * @return {@code AdminCreateUserResponse} response from Cognito IDP Client on user creation
     */
    AdminCreateUserResponse signUp(String email, String password);

    /**
     * Contract for initiating requested user authentication on behalf of admin by email and password.
     *
     * @param email {@code String} requested email
     * @param password {@code String} requested password
     * @return {@code AdminInitiateAuthResponse} response from Cognito IDP Client on user authentication operation
     */
    AdminInitiateAuthResponse signIn(String email, String password);

    /**
     * Contract for getting user on behalf of admin by email.
     *
     * @param email {@code String} email
     * @return {@code AdminGetUserResponse} response from Cognito IDP Client on admin get user request operation
     */
    AdminGetUserResponse getUser(String email);

    /**
     * Contract for getting 'sub' attribute from the user profile in Cognito IDP.
     *
     * @param signedUpUser {@code UserType} user
     * @return {@code String} found 'sub' attribute of the user profile in Cognito IDP
     */
    String getSubId(UserType signedUpUser);

    /**
     * Contract for generating accessToken by email and password.
     *
     * @param email {@code String} requested email
     * @param password {@code String} requested password
     * @return {@code String} generated accessToken from authentication response from Cognito IDP
     */
    String getAccessToken(String email, String password);
}