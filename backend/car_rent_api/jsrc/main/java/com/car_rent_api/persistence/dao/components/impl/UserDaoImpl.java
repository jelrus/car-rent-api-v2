package com.car_rent_api.persistence.dao.components.impl;

import com.car_rent_api.config.Resources;
import com.car_rent_api.config.TableKeys;
import com.car_rent_api.persistence.dao.components.UserDao;
import com.car_rent_api.persistence.models.entity.SupportAgent;
import com.car_rent_api.persistence.models.entity.User;
import com.car_rent_api.persistence.models.entity.types.UserRole;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private final DynamoDbTable<User> userVolume;
    private final DynamoDbTable<SupportAgent> supportAgentVolume;

    public UserDaoImpl(DynamoDbEnhancedClient dbClient) {
        this.userVolume = dbClient.table(Resources.DYNAMO_DATABASE, TableSchema.fromClass(User.class));
        this.supportAgentVolume = dbClient.table(Resources.DYNAMO_DATABASE, TableSchema.fromClass(SupportAgent.class));
    }

    @Override
    public User put(User user) {
        PutItemEnhancedRequest<User> userRequest = PutItemEnhancedRequest.builder(User.class).item(user).build();
        userVolume.putItem(userRequest);
        return user;
    }

    @Override
    public User findById(String id) {
        Key userKey = Key.builder().partitionValue(TableKeys.USER_PK).sortValue(TableKeys.USER_SK_PREFIX + id).build();
        GetItemEnhancedRequest userRequest = GetItemEnhancedRequest.builder().key(userKey).build();
        return userVolume.getItem(userRequest);
    }

    @Override
    public Boolean isExistsById(String id) {
        Key userKey = Key.builder().partitionValue(TableKeys.USER_PK).sortValue(TableKeys.USER_SK_PREFIX + id).build();
        GetItemEnhancedRequest userRequest = GetItemEnhancedRequest.builder().key(userKey).build();
        return userVolume.getItem(userRequest) != null;
    }

    @Override
    public UserRole assignRole(String email) {
        return isSupportAgent(email) ? UserRole.SUPPORT_AGENT : UserRole.CLIENT;
    }

    public Boolean isSupportAgent(String email) {
        Key supportAgentKey = Key.builder()
                .partitionValue(TableKeys.SUPPORT_AGENTS_PK)
                .sortValue(TableKeys.SUPPORT_AGENTS_SK)
                .build();

        GetItemEnhancedRequest supportAgentsRequest = GetItemEnhancedRequest.builder().key(supportAgentKey).build();

        return supportAgentVolume.getItem(supportAgentsRequest) != null &&
               supportAgentVolume.getItem(supportAgentsRequest).getEmails() != null &&
               supportAgentVolume.getItem(supportAgentsRequest).getEmails().contains(email);
    }

    @Override
    public List<User> findSupportAgents() {
        Key userKey = Key.builder().partitionValue(TableKeys.USER_PK).sortValue(TableKeys.USER_SK_PREFIX).build();

        QueryConditional feedbackCondition = QueryConditional.sortBeginsWith(userKey);
        QueryEnhancedRequest feedbackRequest = QueryEnhancedRequest.builder()
                .filterExpression(Expression.builder()
                        .expression("#attr = :attr")
                        .expressionNames(Map.of("#attr", "USER#ROLE"))
                        .expressionValues(Map.of(":attr", AttributeValue.builder().s("SUPPORT_AGENT").build()))
                        .build())
                .queryConditional(feedbackCondition)
                .build();

        return userVolume.query(feedbackRequest).items().stream().toList();
    }
}