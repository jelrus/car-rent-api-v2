package com.api.service.components.impl;

import com.api.exception.ExistenceException;
import com.api.exception.OperationFailedException;
import com.api.persistence.dao.components.AuthDao;
import com.api.persistence.dao.components.UserDao;
import com.api.persistence.models.dto.users.UserLoginRequest;
import com.api.persistence.models.dto.users.UserLoginResponse;
import com.api.persistence.models.dto.users.UserSignUpRequest;
import com.api.persistence.models.dto.users.UserSignUpResponse;
import com.api.persistence.models.entity.User;
import com.api.service.components.AuthService;
import com.api.utils.components.LogPrinter;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;

public class AuthServiceImpl implements AuthService {

    private final AuthDao authDao;
    private final UserDao userDao;

    public AuthServiceImpl(AuthDao authDao, UserDao userDao) {
        this.authDao = authDao;
        this.userDao = userDao;
    }

    @Override
    public UserSignUpResponse signUp(UserSignUpRequest request) {
        LogPrinter.warn("[AuthService | Sign Up] Entering 'signUp @ AuthService' method");

        String email = request.getEmail();
        String password = request.getPassword();
        String subId;

        try {
            checkUserPoolExistence(email);
            String accessToken = authDao.signUp(email, password);
            checkUserPoolNonExistence(email);
            subId = authDao.getSubId(email);
            LogPrinter.info("[AuthService | Sign Up] User in Cognito User Pool was created with id {}", subId);

            checkDbExistence(subId);
            userDao.create(buildUser(subId, request));
            checkDbNonExistence(subId);
            User foundUser = userDao.findById(subId);
            LogPrinter.info("[AuthService | Sign Up] User in DynamoDB was created with id {} and username {}",
                    subId, foundUser.getUsername());

            UserSignUpResponse response = buildUserSignUpResponse(accessToken, foundUser);
            LogPrinter.info("[AuthService | Sign Up] Exiting 'signUp @ AuthService' with response (id {}; " +
                    "username {})", response.getUserId(), response.getUsername());

            return response;
        } catch (ExistenceException | CognitoIdentityProviderException | SdkClientException operationException) {
            LogPrinter.error("[AuthService | Sign Up] Exiting 'signUp @ AuthService' with error {}",
                    operationException.getMessage());
            throw new OperationFailedException("Could not sign up.");
        }
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        LogPrinter.warn("[AuthService | Log In] Entering 'login @ AuthService' method");

        String email = request.getEmail();
        String password = request.getPassword();
        String subId;

        try {
            checkUserPoolNonExistence(email);
            String accessToken = authDao.signIn(email, password);
            subId = authDao.getSubId(email);
            LogPrinter.info("[AuthService | Log In] User was found in Cognito User Pool with id {}", subId);

            checkDbNonExistence(subId);
            User foundUser = userDao.findById(subId);
            LogPrinter.info("[AuthService | Log In] User was found in DynamoDB with id {} and username {}",
                    subId, foundUser.getUsername());

            UserLoginResponse response = buildUserLoginResponse(accessToken, foundUser);
            LogPrinter.info("[AuthService | Log In] Exiting 'login @ AuthService' with response (id {}; " +
                    "username {})", response.getUserId(), response.getUsername());

            return response;
        } catch (ExistenceException | CognitoIdentityProviderException | SdkClientException operationException) {
            LogPrinter.error("[AuthService | Log In] Exiting 'login @ AuthService' with error {}",
                    operationException.getMessage());
            throw new OperationFailedException("Could not log in.");
        }
    }

    private void checkUserPoolExistence(String email) {
        if (authDao.isExistsByEmail(email)) {
            LogPrinter.error("[AuthService] Email {} already exists in user pool", email);
            throw new ExistenceException("Email is already exists in user pool");
        }
    }

    private void checkUserPoolNonExistence(String email) {
        if (!authDao.isExistsByEmail(email)) {
            LogPrinter.error("[AuthService] Email {} is not exists in user pool", email);
            throw new ExistenceException("Email does not exist in user pool");
        }
    }

    private void checkDbExistence(String id) {
        if (userDao.isExistsById(id)) {
            LogPrinter.error("[AuthService] Profile with id {} is already exists in database", id);
            throw new ExistenceException("Profile is already exists in database");
        }
    }

    private void checkDbNonExistence(String id) {
        if (!userDao.isExistsById(id)) {
            LogPrinter.error("[AuthService] Profile with id {} is not exists in database", id);
            throw new ExistenceException("Profile does not exist in database");
        }
    }

    private User buildUser(String subId, UserSignUpRequest userSignUpRequest) {
        return User.builder()
                .pkId().skId(subId)
                .firstName(userSignUpRequest.getFirstName())
                .lastName(userSignUpRequest.getLastName())
                .username()
                .imageUrl("")
                .role(userDao.assignRole(userSignUpRequest.getEmail()))
                .build();
    }

    private UserSignUpResponse buildUserSignUpResponse(String accessToken, User user) {
        return UserSignUpResponse.builder()
                .userId(user.getSkId())
                .accessToken(accessToken)
                .role(user.getRole())
                .userImageUrl(user.getImageUrl())
                .username(user.getUsername())
                .build();
    }

    private UserLoginResponse buildUserLoginResponse(String accessToken, User user) {
        return UserLoginResponse.builder()
                .userId(user.getSkId())
                .accessToken(accessToken)
                .role(user.getRole())
                .userImageUrl(user.getImageUrl())
                .username(user.getUsername())
                .build();
    }
}