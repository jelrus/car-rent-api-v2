package com.backend.service.impl;

import com.backend.dao.AuthDao;
import com.backend.dao.UserDao;
import com.backend.exception.CognitoException;
import com.backend.exception.UserNotFoundException;
import com.backend.models.dto.request.UserSignInRequest;
import com.backend.models.dto.response.UserSignInResponse;
import com.backend.models.dto.request.UserSignUpRequest;
import com.backend.models.dto.response.UserSignUpResponse;
import com.backend.models.table.User;
import com.backend.models.table.types.UserRole;
import com.backend.service.AuthService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserType;

public class AuthServiceImpl implements AuthService {

    private final AuthDao authDao;
    private final UserDao userDao;
    private final Gson gson;

    public AuthServiceImpl(AuthDao authDao, UserDao userDao, Gson gson) {
        this.authDao = authDao;
        this.userDao = userDao;
        this.gson = gson;
    }

    @Override
    public UserSignUpResponse userSignUp(UserSignUpRequest request) {
        LoggerService.warn("[AuthService | Sign Up] Attempting to process sign up request {}", gson.toJson(request));
        String userId = getSubId(authDao.signUp(request.getEmail(), request.getPassword()).user());
        LoggerService.info("[AuthService | Sign Up] User id acquired {}", userId);

        LoggerService.warn("[AuthService | Sign Up] Attempting to create user in table");
        User user = convertToUser(userId, request);
        userDao.create(convertToUser(userId, request)); // todo change to user
        LoggerService.info("[AuthService | Sign Up] User was successfully created");

        if (userDao.existsByUserId(userId)) {
            LoggerService.info("[AuthService | Sign Up] User was successfully created in table and signed up");
            return convertToUserSignUpResponse(getAccessToken(request.getEmail(), request.getPassword()), user);
        } else {
            LoggerService.error("[AuthService | Sign Up] User was not found in table after sign up");
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public UserSignInResponse userSignIn(UserSignInRequest request) {
        LoggerService.info("[AuthService | Sign In] Attempting to process sign up request {}", gson.toJson(request));
        String userId = authDao.getUser(request.getEmail()).userAttributes().stream()
                .filter(a -> a.name().equals("sub"))
                .findAny()
                .orElseThrow(() -> new CognitoException("sub is not present"))
                .value();
        LoggerService.info("[AuthService | Sign In] User was found in cognito user pool with id {}", userId);

        if (userDao.existsByUserId(userId)) {
            LoggerService.info("[AuthService | Sign In] User was successfully found in table");
            User user = userDao.findByUserId(userId);
            LoggerService.info("[AuthService | Sign In] Processing access token for user");
            return convertToUserSignInResponse(getAccessToken(request.getEmail(), request.getPassword()), user);
        } else {
            LoggerService.info("[AuthService | Sign In] User was not found in table after sign in");
            throw new UserNotFoundException("User not found");
        }
    }

    private String getSubId(UserType signedUpUser) {
        return signedUpUser.attributes().stream()
                .filter(a -> a.name().equals("sub"))
                .findAny()
                .orElseThrow(() -> new CognitoException("sub is not present"))
                .value();
    }

    private String getAccessToken(String email, String password) {
        return authDao.signIn(email, password).authenticationResult().idToken();
    }

    private User convertToUser(String userId, UserSignUpRequest userRequest) {
        User user = new User();
        user.setUserId(userId);
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setImageUrl("");

        if (userDao.inSupportAgentsList(userRequest.getEmail())) {
            user.setRole(UserRole.SUPPORT_AGENT);
        } else {
            user.setRole(UserRole.CLIENT);
        }

        return user;
    }

    private UserSignUpResponse convertToUserSignUpResponse(String accessToken, User user) {
        UserSignUpResponse userSignUpResponse = new UserSignUpResponse();
        userSignUpResponse.setAccessToken(accessToken);
        userSignUpResponse.setUserId(user.getUserId());
        userSignUpResponse.setRole(user.getRole().getRole());
        userSignUpResponse.setUserImageUrl(user.getImageUrl());
        userSignUpResponse.setUsername(user.getFirstName() + " " + user.getLastName());
        return userSignUpResponse;
    }

    private UserSignInResponse convertToUserSignInResponse(String accessToken, User user) {
        UserSignInResponse userSignInResponse = new UserSignInResponse();
        userSignInResponse.setAccessToken(accessToken);
        userSignInResponse.setUserId(user.getUserId());
        userSignInResponse.setRole(user.getRole().getRole());
        userSignInResponse.setUserImageUrl(user.getImageUrl());
        userSignInResponse.setUsername(user.getFirstName() + " " + user.getLastName());
        return userSignInResponse;
    }
}
