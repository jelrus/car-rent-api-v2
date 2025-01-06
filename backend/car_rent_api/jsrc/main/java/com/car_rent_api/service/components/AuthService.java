package com.car_rent_api.service.components;

import com.car_rent_api.persistence.models.dto.users.UserLoginRequest;
import com.car_rent_api.persistence.models.dto.users.UserLoginResponse;
import com.car_rent_api.persistence.models.dto.users.UserSignUpRequest;
import com.car_rent_api.persistence.models.dto.users.UserSignUpResponse;

public interface AuthService {

    UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest);

    UserLoginResponse login(UserLoginRequest userLoginRequest);
}