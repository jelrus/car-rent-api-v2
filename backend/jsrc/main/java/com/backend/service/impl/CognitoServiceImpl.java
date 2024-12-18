package com.backend.service.impl;

import com.backend.dto.UserSignUpRequest;
import com.backend.service.CognitoService;

public class CognitoServiceImpl implements CognitoService {

    @Override
    public String getAccessToken(UserSignUpRequest request) {
        // todo
        return "access token";
    }
}
