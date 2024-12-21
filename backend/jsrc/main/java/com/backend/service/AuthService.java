package com.backend.service;

import com.backend.models.dto.request.UserSignInRequest;
import com.backend.models.dto.response.UserSignInResponse;
import com.backend.models.dto.request.UserSignUpRequest;
import com.backend.models.dto.response.UserSignUpResponse;

public interface AuthService {

    UserSignUpResponse userSignUp(UserSignUpRequest userSignUpRequest);

    UserSignInResponse userSignIn(UserSignInRequest userSignInRequest);
}
