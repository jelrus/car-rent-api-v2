package com.car_rent_api.persistence.dao.components.impl;

import com.car_rent_api.config.Resources;
import com.car_rent_api.config.TableKeys;
import com.car_rent_api.persistence.dao.components.LocationDao;
import com.car_rent_api.persistence.models.entity.Location;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;

public class LocationDaoImpl implements LocationDao {

    private final DynamoDbTable<Location> locationVolume;

    public LocationDaoImpl(DynamoDbEnhancedClient dbClient) {
        this.locationVolume = dbClient.table(Resources.DYNAMO_DATABASE, TableSchema.fromClass(Location.class));
    }

    @Override
    public Location findById(String id) {
        Key locationKey = Key.builder()
                .partitionValue(TableKeys.LOCATION_PK)
                .sortValue(TableKeys.LOCATION_SK_PREFIX + id)
                .build();
        GetItemEnhancedRequest locationRequest = GetItemEnhancedRequest.builder().key(locationKey).build();
        return locationVolume.getItem(locationRequest);
    }

    @Override
    public Boolean isExistById(String id) {
        Key locationKey = Key.builder()
                .partitionValue(TableKeys.LOCATION_PK)
                .sortValue(TableKeys.LOCATION_SK_PREFIX + id)
                .build();
        GetItemEnhancedRequest locationRequest = GetItemEnhancedRequest.builder().key(locationKey).build();
        return locationVolume.getItem(locationRequest) != null;
    }

    @Override
    public List<Location> findAll() {
        Key locationKey = Key.builder()
                .partitionValue(TableKeys.LOCATION_PK)
                .sortValue(TableKeys.LOCATION_SK_PREFIX)
                .build();
        QueryConditional locationCondition = QueryConditional.sortBeginsWith(locationKey);
        return locationVolume.query(locationCondition).stream().map(Page::items).flatMap(List::stream).toList();
    }
}