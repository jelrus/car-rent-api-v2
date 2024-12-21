package com.backend.dao;

import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminGetUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthResponse;

public interface AuthDao {

    AdminCreateUserResponse signUp(String email, String password);

    AdminInitiateAuthResponse signIn(String email, String password);

    AdminGetUserResponse getUser(String email);
}