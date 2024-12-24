package com.backend.service.components.impl;

import com.backend.dao.components.AuthDao;
import com.backend.dao.components.UserDao;
import com.backend.exception.AuthException;
import com.backend.exception.UserNotFoundException;
import com.backend.mapper.UserMapper;
import com.backend.models.dto.request.auth.UserSignInRequest;
import com.backend.models.dto.response.auth.UserSignInResponse;
import com.backend.models.dto.request.auth.UserSignUpRequest;
import com.backend.models.dto.response.auth.UserSignUpResponse;
import com.backend.models.table.User;
import com.backend.service.components.AuthService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminGetUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserType;

/**
 * AuthServiceImpl is the implementation of AuthService interface, provides business logic operations on auth entities,
 * serves as mediator between handlers and dao layers.
 */
public class AuthServiceImpl implements AuthService {

    /**
     * Provides AuthDao object for low-level requests to Cognito IDP Client.
     */
    private final AuthDao authDao;

    /**
     * Provides UserDao object for low-level requests to DynamoDB Client.
     */
    private final UserDao userDao;

    /**
     * Provides Gson for mapping objects into JSON format.
     */
    private final Gson gson;

    /**
     * Constructs AuthServiceImpl object with injected AuthDao, UserDao and Gson.
     *
     * @param authDao {@code AuthDao} injected AuthDao
     * @param userDao {@code UserDao} injected UserDao
     * @param gson {@code Gson} injected Gson
     */
    public AuthServiceImpl(AuthDao authDao, UserDao userDao, Gson gson) {
        this.authDao = authDao;
        this.userDao = userDao;
        this.gson = gson;
    }

    /**
     * Creates user profile in Cognito User Pool (credentials) and DynamoDB table (custom user's profile attributes).
     *
     * @param request {@code UserSignUpRequest} specified UserSignUpRequest for creation
     * @return {@code UserSignUpResponse} acquired response as result of create operation
     */
    @Override
    public UserSignUpResponse userSignUp(UserSignUpRequest request) {
        String userId;

        try {
            LoggerService.warn("[AuthService | Sign Up] Attempting to process sign up request {}",
                    gson.toJson(request));
            userId = authDao.getSubId(authDao.signUp(request.getEmail(), request.getPassword()).user());
            LoggerService.info("[AuthService | Sign Up] User id acquired {}", userId);
        } catch (CognitoIdentityProviderException | SdkClientException awsException) {
            LoggerService.error("[AuthService | Sign Up] Cognito identity provider has failed with {}",
                    gson.toJson(awsException.getMessage()));
            throw new AuthException("[AuthService | Sign Up] Sign up operation has failed");
        }

        LoggerService.warn("[AuthService | Sign Up] Attempting to create user in table");
        User user = UserMapper.convertToUser(userId, userDao.getRole(request.getEmail()), request);
        userDao.create(user);
        LoggerService.info("[AuthService | Sign Up] User was successfully created");

        if (userDao.existsByUserId(userId)) {
            LoggerService.info("[AuthService | Sign Up] User was successfully created in table and signed up");
            return UserMapper.convertToUserSignUpResponse(
                    authDao.getAccessToken(request.getEmail(), request.getPassword()), user
            );
        } else {
            LoggerService.error("[AuthService | Sign Up] User with id {} was not found in table after sign up", userId);
            throw new UserNotFoundException("[AuthService | Sign Up] Sign up operation has failed");
        }
    }

    /**
     * Retrieves user profile from DynamoDB table (custom user's profile attributes) by existing credentials in Cognito
     * User Pool and provides access token for security requests.
     *
     * @param request {@code UserSignInRequest} specified UserSignInRequest for signing in
     * @return {@code UserSignInResponse} acquired response as result of signing in operation
     */
    @Override
    public UserSignInResponse userSignIn(UserSignInRequest request) {
        String userId;

        try {
            LoggerService.info("[AuthService | Sign In] Attempting to process sign up request {}",
                    gson.toJson(request));
            AdminGetUserResponse userGetResponse = authDao.getUser(request.getEmail());
            userId = authDao.getSubId(UserType.builder().attributes(userGetResponse.userAttributes()).build());
            LoggerService.info("[AuthService | Sign In] User was found in cognito user pool with id {}", userId);
        } catch (CognitoIdentityProviderException | SdkClientException awsException) {
            LoggerService.error("[AuthService | Sign In] Cognito identity provider has failed with {}",
                    gson.toJson(awsException.getMessage()));
            throw new AuthException("[AuthService | Sign In] Sign in operation has failed");
        }

        if (userDao.existsByUserId(userId)) {
            LoggerService.info("[AuthService | Sign In] User was successfully found in table");
            User user = userDao.findByUserId(userId);
            LoggerService.info("[AuthService | Sign In] Processing access token for user");
            return UserMapper.convertToUserSignInResponse(
                    authDao.getAccessToken(request.getEmail(), request.getPassword()), user
            );
        } else {
            LoggerService.info("[AuthService | Sign In] User was not found in table after sign in");
            throw new UserNotFoundException("User was not found");
        }
    }
}
