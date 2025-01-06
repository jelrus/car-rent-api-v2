package com.api.persistence.models.dto.users;

import com.api.config.TableKeys;
import com.api.persistence.models.entity.types.UserRole;
import com.google.gson.annotations.Expose;

public class UserLoginResponse {

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

    private UserLoginResponse() {}

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
        return new UserLoginResponse().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder accessToken(String accessToken) {
            UserLoginResponse.this.accessToken = accessToken;
            return this;
        }

        public Builder role(UserRole role) {
            UserLoginResponse.this.role = role.getName();
            return this;
        }

        public Builder userId(String userId) {
            UserLoginResponse.this.userId = userId.replace(TableKeys.USER_SK_PREFIX, "");
            return this;
        }

        public Builder userImageUrl(String userImageUrl) {
            UserLoginResponse.this.userImageUrl = userImageUrl;
            return this;
        }

        public Builder username(String username) {
            UserLoginResponse.this.username = username;
            return this;
        }

        public UserLoginResponse build() {
            return UserLoginResponse.this;
        }
    }
}