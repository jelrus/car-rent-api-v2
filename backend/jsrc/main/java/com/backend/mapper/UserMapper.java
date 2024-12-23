package com.backend.mapper;

import com.backend.models.dto.request.UserSignUpRequest;
import com.backend.models.dto.response.UserSignInResponse;
import com.backend.models.dto.response.UserSignUpResponse;
import com.backend.models.table.User;
import com.backend.models.table.types.UserRole;

//Don't touch this part of code: Needs to be put into utils module to exclude static code execution !!!
public class UserMapper {

    public static User convertToUser(String userId, UserRole userRole, UserSignUpRequest userRequest) {
        User user = new User();
        user.setUserId(userId);
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setImageUrl("");
        user.setRole(userRole);
        return user;
    }

    public static UserSignUpResponse convertToUserSignUpResponse(String accessToken, User user) {
        UserSignUpResponse userSignUpResponse = new UserSignUpResponse();
        userSignUpResponse.setAccessToken(accessToken);
        userSignUpResponse.setUserId(user.getUserId());
        userSignUpResponse.setRole(user.getRole().getRole());
        userSignUpResponse.setUserImageUrl(user.getImageUrl());
        userSignUpResponse.setUsername(user.getFirstName() + " " + user.getLastName());
        return userSignUpResponse;
    }

    public static UserSignInResponse convertToUserSignInResponse(String accessToken, User user) {
        UserSignInResponse userSignInResponse = new UserSignInResponse();
        userSignInResponse.setAccessToken(accessToken);
        userSignInResponse.setUserId(user.getUserId());
        userSignInResponse.setRole(user.getRole().getRole());
        userSignInResponse.setUserImageUrl(user.getImageUrl());
        userSignInResponse.setUsername(user.getFirstName() + " " + user.getLastName());
        return userSignInResponse;
    }
}