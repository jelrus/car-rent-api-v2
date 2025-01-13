package com.car_rent_api.utils.components;

import com.car_rent_api.config.TableKeys;
import com.car_rent_api.exception.OperationFailedException;
import com.car_rent_api.persistence.dao.components.AuthDao;
import com.car_rent_api.persistence.dao.components.UserDao;
import com.car_rent_api.persistence.models.entity.User;
import com.car_rent_api.persistence.models.entity.types.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EndpointHandlerAuthorizer {

    private final UserDao userDao;
    private final AuthDao authDao;
    private String accessToken;
    private String targetId;
    private String subId;
    private User authUser;
    private User operationUser;
    private List<Boolean> rules;

    public EndpointHandlerAuthorizer(UserDao userDao, AuthDao authDao) {
        this.userDao = userDao;
        this.authDao = authDao;
    }

    public String getSubId() {
        return subId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTargetId() {
        return targetId;
    }

    public User getAuthUser() {
        return authUser;
    }

    public User getOperationUser() {
        return operationUser;
    }

    public List<Boolean> getRules() {
        return rules;
    }

    public Builder secure() {
        return this.new Builder();
    }

    public class Builder {

        private Builder() {
            rules = new ArrayList<>();
        }

        public Builder accessToken(String accessToken) {
            EndpointHandlerAuthorizer.this.accessToken = accessToken;
            EndpointHandlerAuthorizer.this.subId = authDao.getSubFromJwt(accessToken);
            EndpointHandlerAuthorizer.this.authUser = userDao.findById(subId);
            return this;
        }

        public Builder targetId(String targetId) {
            EndpointHandlerAuthorizer.this.targetId = targetId;
            EndpointHandlerAuthorizer.this.operationUser = userDao.findById(targetId);
            return this;
        }

        public Builder checkNullity() {
            Boolean isNull = accessToken == null || subId == null || targetId == null;
            EndpointHandlerAuthorizer.this.rules.add(isNull);
            return this;
        }

        public Builder checkUsersExistence() {
            Boolean isExist = !userDao.isExistsById(subId) || !userDao.isExistsById(targetId);
            EndpointHandlerAuthorizer.this.rules.add(isExist);
            return this;
        }

        public Builder prohibitForRoles(UserRole... roles) {
            Arrays.stream(roles).forEach(r -> {
                Boolean haveRole = authUser.getRole() == r;
                EndpointHandlerAuthorizer.this.rules.add(haveRole);
            });

            return this;
        }

        public Builder prohibitForTargetRoles(UserRole accessRole, UserRole... roles) {
            Arrays.stream(roles).forEach(r -> {
                Boolean haveTargetRole = authUser.getRole() == accessRole && operationUser.getRole() == r;
                EndpointHandlerAuthorizer.this.rules.add(haveTargetRole);
            });

            return this;
        }

        public Builder prohibitForNotSelfTargetId(UserRole userRole) {
            boolean isSelfId = authUser.getSkId().replace(TableKeys.USER_SK_PREFIX, "").equals(targetId);
            Boolean haveRoleAndSelfId = authUser.getRole() == userRole && !isSelfId;
            EndpointHandlerAuthorizer.this.rules.add(haveRoleAndSelfId);
            return this;
        }

        public Builder prohibitForSelfTargetId(UserRole userRole) {
            boolean isSelfId = authUser.getSkId().replace(TableKeys.USER_SK_PREFIX, "").equals(targetId);
            Boolean haveRoleAndSelfId = authUser.getRole() == userRole && isSelfId;
            EndpointHandlerAuthorizer.this.rules.add(haveRoleAndSelfId);
            return this;
        }

        public void build() {
            for (Boolean prohibit : rules) {
                if (prohibit) {
                    throw new OperationFailedException("Permission denied");
                }
            }
        }
    }
}