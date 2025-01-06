package com.backend.dao.components.impl;

import com.backend.dao.components.LocationDao;
import com.backend.models.table.Location;
import com.backend.utils.properties.Envs;
import com.google.gson.Gson;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

public class LocationDaoImpl implements LocationDao {

    private final DynamoDbTable<Location> locationsTable;

    private final Gson gson;

    public LocationDaoImpl(DynamoDbEnhancedClient dbClient, Gson gson) {
        this.locationsTable = dbClient.table(Envs.LOCATIONS_TABLE, TableSchema.fromClass(Location.class));
        this.gson = gson;
    }

    @Override
    public List<Location> findAll() {
        return locationsTable.scan().items().stream().toList();
    }
}