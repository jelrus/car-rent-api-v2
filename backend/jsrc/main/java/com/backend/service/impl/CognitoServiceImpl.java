package com.backend.service.impl;

import com.backend.service.CognitoService;
import com.backend.utils.components.Envs;
import com.backend.utils.services.LoggerService;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.Map;

/**
 *  A service to work with cognito pool
 */
public class CognitoServiceImpl implements CognitoService {

    private final CognitoIdentityProviderClient cognitoClient;

    public CognitoServiceImpl(CognitoIdentityProviderClient cognitoClient) {
        this.cognitoClient = cognitoClient;
    }

    @Override
    public void addUserToCognito(String email, String password) {

        LoggerService.info("addUserToCognito");

        // creating new user in cognito pool
        AdminCreateUserRequest createUserRequest = AdminCreateUserRequest.builder()
                .userPoolId(Envs.COGNITO_ID)
                .username(email)
                .temporaryPassword(password)
                .build();

        AdminCreateUserResponse createUserResponse = cognitoClient.adminCreateUser(createUserRequest);

        // setting permanent password
        AdminSetUserPasswordRequest setPasswordRequest = AdminSetUserPasswordRequest.builder()
                .userPoolId(Envs.COGNITO_ID)
                .username(email)
                .password(password)
                .permanent(true)
                .build();

        cognitoClient.adminSetUserPassword(setPasswordRequest);

        LoggerService.info("User created successfully: {}", createUserResponse.user().username());
    }

    @Override
    public String getAccessToken(String email, String password) {

        LoggerService.info("getAccessToken");
        // authenticating of user
        InitiateAuthRequest authRequest = InitiateAuthRequest.builder()
                .authFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .clientId(Envs.CLIENT_ID)
                .authParameters(Map.of(
                        "USERNAME", email,
                        "PASSWORD", password
                ))
                .build();

        InitiateAuthResponse authResponse = cognitoClient.initiateAuth(authRequest);

        return authResponse.authenticationResult().idToken();
    }
}
