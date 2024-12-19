package com.backend.service.impl;

import com.backend.service.CognitoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.Map;

public class CognitoServiceImpl implements CognitoService {

    private static final String REGION = System.getenv("REGION");
    private final String cognitoId = System.getenv("COGNITO_ID");
    private final String clientId = System.getenv("CLIENT_ID");
    private static final Logger logger = LoggerFactory.getLogger(CognitoServiceImpl.class);
    private final CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
            .region(Region.of(REGION))
            .build();

    @Override
    public void addUserToCognito(String email, String password) {

        logger.info("addUserToCognito");

        AdminCreateUserRequest createUserRequest = AdminCreateUserRequest.builder()
                .userPoolId(cognitoId)
                .username(email)
                .temporaryPassword(password)
                .build();

        AdminCreateUserResponse createUserResponse = cognitoClient.adminCreateUser(createUserRequest);

        // setting permanent password
        AdminSetUserPasswordRequest setPasswordRequest = AdminSetUserPasswordRequest.builder()
                .userPoolId(cognitoId)
                .username(email)
                .password(password)
                .permanent(true)
                .build();

        cognitoClient.adminSetUserPassword(setPasswordRequest);

        logger.info("User created successfully: {}", createUserResponse.user().username());
    }

    @Override
    public String getAccessToken(String email, String password) {

        logger.info("getAccessToken");

        InitiateAuthRequest authRequest = InitiateAuthRequest.builder()
                .authFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .clientId(clientId)
                .authParameters(Map.of(
                        "USERNAME", email,
                        "PASSWORD", password
                ))
                .build();

        InitiateAuthResponse authResponse = cognitoClient.initiateAuth(authRequest);

        return authResponse.authenticationResult().idToken();
    }
}
