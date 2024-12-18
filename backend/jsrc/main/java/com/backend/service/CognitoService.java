package com.backend.service;

import com.backend.dto.UserSignUpRequest;

public interface CognitoService {

    String getAccessToken(UserSignUpRequest request);
}
