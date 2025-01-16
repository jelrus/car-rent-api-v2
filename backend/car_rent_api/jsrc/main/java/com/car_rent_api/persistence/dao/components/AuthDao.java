package com.car_rent_api.persistence.dao.components;

public interface AuthDao {

    String signUp(String email, String password);

    String signIn(String email, String password);

    String changePassword(String accessToken, String email, String oldPassword, String newPassword);

    String getSubId(String email);

    Boolean isExistsByEmail(String email);

    String getSubFromJwt(String jwt);

    String getEmailFromJwt(String jwt);
}