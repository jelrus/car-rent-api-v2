package com.car_rent_api.service.components.impl;

import com.car_rent_api.exception.ExistenceException;
import com.car_rent_api.exception.OperationFailedException;
import com.car_rent_api.persistence.dao.components.AuthDao;
import com.car_rent_api.persistence.dao.components.UserDao;
import com.car_rent_api.persistence.models.dto.users.*;
import com.car_rent_api.persistence.models.entity.User;
import com.car_rent_api.service.components.AuthService;
import com.car_rent_api.utils.components.LogPrinter;
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
        String email = request.getEmail();
        String password = request.getPassword();
        String subId;

        try {
            checkUserPoolExistence(email);
            String accessToken = authDao.signUp(email, password);
            subId = authDao.getSubId(email);

            checkDbExistence(subId);
            userDao.put(buildUser(subId, request));
            User foundUser = userDao.findById(subId);

            return buildUserSignUpResponse(accessToken, foundUser);
        } catch (ExistenceException | CognitoIdentityProviderException | SdkClientException operationException) {
            LogPrinter.error("[AuthService | Sign Up] Exiting 'signUp @ AuthService' with error {}",
                    operationException.getMessage());
            throw new OperationFailedException("Sign up failed. Recheck your email and password");
        }
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String subId;

        try {
            checkUserPoolNonExistence(email);
            String accessToken = authDao.signIn(email, password);
            subId = authDao.getSubId(email);

            checkDbNonExistence(subId);
            User foundUser = userDao.findById(subId);

            return buildUserLoginResponse(accessToken, foundUser);
        } catch (ExistenceException | CognitoIdentityProviderException | SdkClientException operationException) {
            LogPrinter.error("[AuthService | Log In] Exiting 'login @ AuthService' with error {}",
                    operationException.getMessage());
            throw new OperationFailedException("Login failed. Recheck your email and password.");
        }
    }

    @Override
    public ChangeUserPasswordResponse changePassword(String accessToken, ChangeUserPasswordRequest request) {
        try {
            String email = authDao.getEmailFromJwt(accessToken);
            checkUserPoolNonExistence(email);

            String newAccessToken =
                    authDao.changePassword(accessToken, email, request.getOldPassword(), request.getNewPassword());
            String subId = authDao.getSubId(email);

            checkDbNonExistence(subId);
            User foundUser = userDao.findById(subId);

            return buildUserChangePasswordResponse(newAccessToken, foundUser);
        } catch (ExistenceException operationException) {
            LogPrinter.error("[AuthService | Change password] Exiting 'change password @ AuthService' with error {}",
                    operationException.getMessage());
            throw new OperationFailedException("Password cannot failed." + operationException.getMessage());
        } catch (CognitoIdentityProviderException | SdkClientException operationException) {
            throw new OperationFailedException("Password change failed. Please, enter you current password and " +
                    "recheck new password.");
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
                .email(userSignUpRequest.getEmail())
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

    private ChangeUserPasswordResponse buildUserChangePasswordResponse(String accessToken, User user) {
        return ChangeUserPasswordResponse.builder()
                .accessToken(accessToken)
                .role(user.getRole().getName())
                .userId(user.getSkId())
                .userImageUrl(user.getImageUrl())
                .username(user.getUsername())
                .userId(user.getSkId())
                .build();
    }
}