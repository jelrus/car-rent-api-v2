package com.backend.service;

import com.backend.models.dto.request.UserSignUpRequest;
import com.backend.models.dto.response.UserSignUpResponse;

public interface UserService {

    UserSignUpResponse create(UserSignUpRequest userSignUpRequest) throws Exception;
}
