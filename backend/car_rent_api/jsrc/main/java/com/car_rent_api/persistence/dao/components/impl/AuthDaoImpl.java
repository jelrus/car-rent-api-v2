package com.car_rent_api.persistence.dao.components.impl;

import com.car_rent_api.config.Resources;
import com.car_rent_api.persistence.dao.components.AuthDao;
import com.auth0.jwt.JWT;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.Map;
import java.util.Optional;

public class AuthDaoImpl implements AuthDao {

    private final CognitoIdentityProviderClient cognitoIpc;

    public AuthDaoImpl(CognitoIdentityProviderClient cognitoIpc) {
        this.cognitoIpc = cognitoIpc;
    }

    @Override
    public String signUp(String email, String password) {
        AdminCreateUserRequest userRequest = AdminCreateUserRequest.builder()
                .userPoolId(Resources.COGNITO_ID)
                .username(email)
                .temporaryPassword(password)
                .build();

        cognitoIpc.adminCreateUser(userRequest);
        verifyUser(email, password);

        return authenticate(email, password);
    }

    @Override
    public String signIn(String email, String password) {
        return authenticate(email, password);
    }

    @Override
    public String getSubId(String email) {
        AdminGetUserRequest userRequest = AdminGetUserRequest.builder()
                .userPoolId(Resources.COGNITO_ID)
                .username(email)
                .build();

        Optional<AttributeType> subId = cognitoIpc.adminGetUser(userRequest).userAttributes()
                .stream()
                .filter(a -> a.name().equals("sub"))
                .findAny();

        return subId.map(AttributeType::value).orElse(null);
    }

    @Override
    public Boolean isExistsByEmail(String email) {
        try {
            AdminGetUserRequest adminGetUserRequest = AdminGetUserRequest.builder()
                    .userPoolId(Resources.COGNITO_ID)
                    .username(email)
                    .build();

            cognitoIpc.adminGetUser(adminGetUserRequest);
            return true;
        } catch (CognitoIdentityProviderException e) {
            return false;
        }
    }

    @Override
    public String getSubFromJwt(String jwt) {
        return JWT.decode(jwt).getSubject();
    }

    private void verifyUser(String email, String password) {
        AdminSetUserPasswordRequest userPasswordRequest = AdminSetUserPasswordRequest.builder()
                .userPoolId(Resources.COGNITO_ID)
                .username(email)
                .password(password)
                .permanent(true)
                .build();

        cognitoIpc.adminSetUserPassword(userPasswordRequest);
    }

    private String authenticate(String email, String password) {
        AdminInitiateAuthResponse authResponse = cognitoIpc.adminInitiateAuth(AdminInitiateAuthRequest.builder()
                .authFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .authParameters(Map.of("USERNAME", email, "PASSWORD", password))
                .userPoolId(Resources.COGNITO_ID)
                .clientId(Resources.CLIENT_ID)
                .build());

        return authResponse.authenticationResult().idToken();
    }
}