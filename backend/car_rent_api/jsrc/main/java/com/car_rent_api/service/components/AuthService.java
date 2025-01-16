package com.car_rent_api.service.components;

import com.car_rent_api.persistence.models.dto.users.*;

public interface AuthService {

    UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest);

    UserLoginResponse login(UserLoginRequest userLoginRequest);

    ChangeUserPasswordResponse changePassword(String accessToken, ChangeUserPasswordRequest changeUserPasswordRequest);
}