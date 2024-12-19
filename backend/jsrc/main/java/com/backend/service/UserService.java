package com.backend.service;

import com.backend.dto.UserSignInResponse;
import com.backend.dto.UserSignUpRequest;
import com.backend.dto.UserSignUpResponse;

public interface UserService {

    // creates user in db
    UserSignUpResponse createUser(UserSignUpRequest userSignUpRequest) throws Exception;

    UserSignInResponse signInUser(String email, String password) throws Exception;
}
