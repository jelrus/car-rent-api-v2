package com.backend.dao.impl;

import com.backend.dao.LocationDao;
import com.backend.models.table.LocationEntity;
import com.backend.utils.components.Envs;
import com.backend.utils.services.LoggerService;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LocationDaoImpl implements LocationDao {

    private final DynamoDbTable<LocationEntity> locationTable;

    public LocationDaoImpl(DynamoDbEnhancedClient dbEnhancedClient) {
        this.locationTable = dbEnhancedClient.table(Envs.LOCATIONS_TABLE, TableSchema.fromClass(LocationEntity.class));
        for (int i = 0; i < 10; i++) {
            create(generateRandomLocationEntity());
        }
    }

    @Override
    public LocationEntity create(LocationEntity locationEntity) {
        LoggerService.warn("[LocationDao|Create] Entered method with Location {}", locationEntity.toString());
        LoggerService.warn("[LocationDao|Create] Attempt to create requested location");
        try {
            PutItemEnhancedRequest<LocationEntity> locationItem = PutItemEnhancedRequest.builder(LocationEntity.class).item(locationEntity).build();
            locationTable.putItem(locationItem);
        } catch (Exception e) {
            LoggerService.warn(e.getMessage());
        }
        LoggerService.info("[LocationDao|Create] Location has been successfully created");
        LoggerService.warn("[LocationDao|Create] Exiting method");
        return locationEntity;
    }


    @Override
    public List<LocationEntity> findAll() {

        PageIterable<LocationEntity> scan = locationTable.scan();

        List<LocationEntity> locations = scan.items().stream().collect(Collectors.toList());

        LoggerService.warn("Total record location " + locations.size());

        return locations;
    }

    private LocationEntity generateRandomLocationEntity() {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setLocationId(UUID.randomUUID().toString());
        locationEntity.setLocationImageUrl("https://application.s3.eu-central-1.amazonaws.com/img/locations/9b903ebf-2b18-4946-bc58-045d86a2632e.jpg");
        locationEntity.setLocationName("Kyiv Hayatt Hotel");
        locationEntity.setLocationAddress("Alla Tarasova Street 5, Kyiv 01001");

        return locationEntity;
    }
}