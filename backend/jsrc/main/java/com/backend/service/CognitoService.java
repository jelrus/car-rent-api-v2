package com.backend.service;

public interface CognitoService {

    // adds new user to cognito pool
    void addUserToCognito(String email, String password);

    // authenticates user and returns access token (id token)
    String getAccessToken(String email, String password);
}
