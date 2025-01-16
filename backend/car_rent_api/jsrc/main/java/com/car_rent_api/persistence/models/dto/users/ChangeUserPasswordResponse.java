package com.car_rent_api.persistence.models.dto.users;

import com.car_rent_api.config.TableKeys;
import com.google.gson.annotations.Expose;

public class ChangeUserPasswordResponse {

    @Expose
    private String accessToken;

    @Expose
    private String role;

    @Expose
    private String userId;

    @Expose
    private String userImageUrl;

    @Expose
    private String username;

    public ChangeUserPasswordResponse() {}

    public String getAccessToken() {
        return accessToken;
    }

    public String getRole() {
        return role;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public String getUsername() {
        return username;
    }

    public static Builder builder() {
        return new ChangeUserPasswordResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder accessToken(String accessToken) {
            ChangeUserPasswordResponse.this.accessToken = accessToken;
            return this;
        }

        public Builder role(String role) {
            ChangeUserPasswordResponse.this.role = role;
            return this;
        }

        public Builder userId(String userId) {
            ChangeUserPasswordResponse.this.userId = userId.replace(TableKeys.USER_SK_PREFIX, "");
            return this;
        }

        public Builder userImageUrl(String userImageUrl) {
            ChangeUserPasswordResponse.this.userImageUrl = userImageUrl;
            return this;
        }

        public Builder username(String username) {
            ChangeUserPasswordResponse.this.username = username;
            return this;
        }

        public ChangeUserPasswordResponse build() {
            return ChangeUserPasswordResponse.this;
        }
    }
}