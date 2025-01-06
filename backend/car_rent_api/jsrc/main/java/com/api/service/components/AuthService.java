package com.api.service.components;

import com.api.persistence.models.dto.users.UserLoginRequest;
import com.api.persistence.models.dto.users.UserLoginResponse;
import com.api.persistence.models.dto.users.UserSignUpRequest;
import com.api.persistence.models.dto.users.UserSignUpResponse;

public interface AuthService {

    UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest);

    UserLoginResponse login(UserLoginRequest userLoginRequest);
}