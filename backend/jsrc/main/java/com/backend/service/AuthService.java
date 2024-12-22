package com.backend.service;

import com.backend.models.dto.request.UserSignInRequest;
import com.backend.models.dto.response.UserSignInResponse;
import com.backend.models.dto.request.UserSignUpRequest;
import com.backend.models.dto.response.UserSignUpResponse;

/**
 * AuthService is the interface, provides contracts for business logic operations on auth entities.
 */
public interface AuthService {

    /**
     * Contract for creating user profile in Cognito User Pool (credentials) and DynamoDB table (custom user's
     * profile attributes).
     *
     * @param request {@code UserSignUpRequest} specified UserSignUpRequest for creation
     * @return {@code UserSignUpResponse} acquired response as result of create operation
     */
    UserSignUpResponse userSignUp(UserSignUpRequest request);

    /**
     * Contract for retrieving user profile from DynamoDB table (custom user's profile attributes) by existing
     * credentials in Cognito User Pool and provides access token for security requests.
     *
     * @param request {@code UserSignInRequest} specified UserSignInRequest for signing in
     * @return {@code UserSignInResponse} acquired response as result of signing in operation
     */
    UserSignInResponse userSignIn(UserSignInRequest request);
}