package com.backend.models.dto.request;

import com.google.gson.annotations.Expose;
import org.json.JSONObject;

public class UserSignInRequest {

    @Expose
    private String email;

    @Expose
    private String password;

    public UserSignInRequest() {
    }

    public UserSignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static UserSignInRequest fromJson(String jsonString) {
        JSONObject json = new JSONObject(jsonString);

        String email = json.optString("email", null);
        String password = json.optString("password", null);

        return new UserSignInRequest(email, password);
    }
}
