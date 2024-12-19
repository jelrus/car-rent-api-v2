package com.backend.service;

import com.backend.dto.UserSignUpRequest;

public interface CognitoService {

    String getAccessToken(String email, String password);

    void addUserToCognito(String email, String password);
}
