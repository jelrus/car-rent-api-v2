package com.backend.dao.impl;

import com.backend.dao.AuthDao;
import com.backend.utils.properties.Envs;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.Map;

public class AuthDaoImpl implements AuthDao {

    private final CognitoIdentityProviderClient cognitoIpc;
    private final Gson gson;

    public AuthDaoImpl(CognitoIdentityProviderClient cognitoIpc, Gson gson) {
        this.cognitoIpc = cognitoIpc;
        this.gson = gson;
    }

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

    @Override
    public AdminGetUserResponse getUser(String email) {
        AdminGetUserRequest getUserRequest = AdminGetUserRequest.builder()
                .userPoolId(Envs.COGNITO_ID)
                .username(email)
                .build();

        return cognitoIpc.adminGetUser(getUserRequest);
    }

    private void updatePassword(String email, String password) {
        LoggerService.warn("[AuthDao | UpdatePassword] Setting password permanent for email {}", email);
        AdminSetUserPasswordRequest setPasswordRequest = AdminSetUserPasswordRequest.builder()
                .userPoolId(Envs.COGNITO_ID)
                .username(email)
                .password(password)
                .permanent(true)
                .build();
        LoggerService.warn("[AuthDao | UpdatePassword Up] User password request for email {} created {}", email);

        LoggerService.warn("[AuthDao | UpdatePassword] Attempting to set password permanent for email {}", email);
        cognitoIpc.adminSetUserPassword(setPasswordRequest);
        LoggerService.warn("[AuthDao | UpdatePassword] Password set to permanent for email {}", email);
    }
}