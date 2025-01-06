package com.api.persistence.dao.components;

public interface AuthDao {

    String signUp(String email, String password);

    String signIn(String email, String password);

    String getSubId(String email);

    Boolean isExistsByEmail(String email);

    String getSubFromJwt(String jwt);
}