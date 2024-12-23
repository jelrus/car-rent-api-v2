package com.backend.dao.impl;

import com.backend.dao.AuthDao;
import com.backend.exception.AuthException;
import com.backend.utils.properties.Envs;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.Map;

/**
 * AuthDaoImpl is the implementation of AuthDao interface, interacts directly with Cognito Identity Provider (IDP)
 * Client, serves as mediator between Cognito Identity Provider Client and service layers.
 */
public class AuthDaoImpl implements AuthDao {

    /**
     * Provides CognitoIdentityProviderClient object for AWS Cognito IDPC interaction within code.
     */
    private final CognitoIdentityProviderClient cognitoIpc;

    /**
     * Provides Gson for mapping objects into JSON format.
     */
    private final Gson gson;

    /**
     * Constructs AuthDaoImpl object with injected Cognito IDP Client and Gson.
     *
     * @param cognitoIpc {@code CognitoIdentityProviderClient} injected Cognito IDP Client
     * @param gson {@code Gson} injected Gson
     */
    public AuthDaoImpl(CognitoIdentityProviderClient cognitoIpc, Gson gson) {
        this.cognitoIpc = cognitoIpc;
        this.gson = gson;
    }

    /**
     * Creates requested user on behalf of admin in Cognito IDP by email and password.
     *
     * @param email {@code String} requested email
     * @param password {@code String} requested password
     * @return {@code AdminCreateUserResponse} response from Cognito IDP Client on user creation
     */
    @Override
    public AdminCreateUserResponse signUp(String email, String password) {
        LoggerService.warn("[AuthDao | Sign Up] Creating user request for email {}", email);
        AdminCreateUserRequest createUserRequest = AdminCreateUserRequest.builder()
                .userPoolId(Envs.COGNITO_ID)
                .username(email)
                .temporaryPassword(password)
                .build();
        LoggerService.warn("[AuthDao | Sign Up] User request for email {} created {}",
                gson.toJson(createUserRequest));

        LoggerService.warn("[AuthDao | Sign Up] Creating user response for email {}", email);
        AdminCreateUserResponse createUserResponse = cognitoIpc.adminCreateUser(createUserRequest);
        LoggerService.warn("[AuthDao | Sign Up] User response for email {} created {}",
                email, gson.toJson(createUserResponse));

        LoggerService.warn("[AuthDao | Sign Up] Attempting to update password for email {}", email);
        updatePassword(email, password);
        LoggerService.warn("[AuthDao | Sign Up] Password for email {} was updated", email);

        LoggerService.warn("[AuthDao | Sign Up] User successfully signed up for email {}", email);
        return createUserResponse;
    }

    /**
     * Initiates requested user authentication on behalf of admin in Cognito IDP by email and password.
     *
     * @param email {@code String} requested email
     * @param password {@code String} requested password
     * @return {@code AdminInitiateAuthResponse} response from Cognito IDP Client on user authentication operation
     */
    @Override
    public AdminInitiateAuthResponse signIn(String email, String password) {
        LoggerService.warn("[AuthDao | Sign In] Attempting to sign in with email {}", email);
        AdminInitiateAuthResponse initiateAuthResponse = cognitoIpc.adminInitiateAuth(AdminInitiateAuthRequest.builder()
                .authFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .authParameters(Map.of("USERNAME", email, "PASSWORD", password))
                .userPoolId(Envs.COGNITO_ID)
                .clientId(Envs.CLIENT_ID)
                .build());

        LoggerService.warn("[AuthDao | Sign In] User with email {} signed in", email);
        return initiateAuthResponse;
    }

    /**
     * Gets user on behalf of admin from Cognito IDP by email.
     *
     * @param email {@code String} email
     * @return {@code AdminGetUserResponse} response from Cognito IDP Client on admin get user request operation
     */
    @Override
    public AdminGetUserResponse getUser(String email) {
        LoggerService.info("[AuthDao | Get User] Forming user get request...");
        AdminGetUserRequest getUserRequest = AdminGetUserRequest.builder()
                .userPoolId(Envs.COGNITO_ID)
                .username(email)
                .build();

        LoggerService.info("[AuthDao | Get User] Attempting to get user with email {}", email);
        return cognitoIpc.adminGetUser(getUserRequest);
    }

    /**
     * Gets 'sub' attribute from the user profile in Cognito IDP.
     *
     * @param signedUpUser {@code UserType} user
     * @return {@code String} found 'sub' attribute of the user profile in Cognito IDP
     */
    @Override
    public String getSubId(UserType signedUpUser) {
        LoggerService.info("[AuthDao | Get Sub Id] Attempting to get sub id");
        return signedUpUser.attributes().stream()
                .filter(a -> a.name().equals("sub"))
                .findAny()
                .orElseThrow(() -> {
                    LoggerService.error("[AuthDao | Get Sub Id] sub attribute was not found");
                    return new AuthException("sub is not present");
                })
                .value();
    }

    /**
     * Generates accessToken from authentication result in Cognito IDP.
     * In this case idToken serves as accessToken.
     *
     * @param email {@code String} requested email
     * @param password {@code String} requested password
     * @return {@code String} generated accessToken from authentication response from Cognito IDP
     */
    @Override
    public String getAccessToken(String email, String password) {
        LoggerService.info("[AuthDao | Get Access Token] Attempting to get access token for email {}", email);
        return signIn(email, password).authenticationResult().idToken();
    }

    /**
     * Sets user's profile password to permanent.
     * <p>
     * In this case email verification step will be bypassed. By default, after the first successful signup user
     * credentials contains temporary password only, which must be changed to permanent from given configured resource,
     * email or SMS, on accessing verification link and changing password.
     *
     * @param email {@code String} requested email
     * @param password {@code String} requested password
     */
    private void updatePassword(String email, String password) {
        LoggerService.warn("[AuthDao | UpdatePassword] Setting password permanent for email {}", email);
        AdminSetUserPasswordRequest setPasswordRequest = AdminSetUserPasswordRequest.builder()
                .userPoolId(Envs.COGNITO_ID)
                .username(email)
                .password(password)
                .permanent(true)
                .build();
        LoggerService.warn("[AuthDao | UpdatePassword Up] User password request for email {} created {}", email);

        LoggerService.warn("[AuthDao | UpdatePassword] Attempting to verify password for email {}", email);
        cognitoIpc.adminSetUserPassword(setPasswordRequest);
        LoggerService.warn("[AuthDao | UpdatePassword] Password has been verified for email {}", email);
    }
}