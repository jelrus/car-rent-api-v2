package com.backend.service;

import com.backend.dto.UserSignUpRequest;
import com.backend.dto.UserSignUpResponse;

public interface CognitoService {

    String getAccessToken(UserSignUpRequest request);
}
