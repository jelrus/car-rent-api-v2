package com.backend.dao.impl;

import com.backend.dao.CarDao;
import com.backend.models.table.CarEntity;
import com.backend.utils.properties.Envs;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Optional;

public class CarDaoImpl implements CarDao {

    private final DynamoDbTable<CarEntity> carTable;
    private final Gson gson;

    public CarDaoImpl(DynamoDbEnhancedClient dbClient, Gson gson) {
        this.carTable = dbClient.table(Envs.CARS_TABLE, TableSchema.fromClass(CarEntity.class));
        this.gson = gson;
    }


    @Override
    public CarEntity findByCarId(String carId) {
        LoggerService.info("[CarDao | Find By Id] Finding car by carId = {}", carId);
        CarEntity car = carTable.getItem(i -> i.key(Key.builder().partitionValue(carId).build()));
        LoggerService.info("[CarDao | Find By Id] Exiting car find by id method with result {}",
                gson.toJson(car));

        return car;
    }

    @Override
    public Boolean existsByCarId(String carId) {
        Key userKey = Key.builder().partitionValue(carId).build();

        LoggerService.info("[CarDao | Exists By carId] Checking existence of car with carId = {}", carId);
        Boolean carExists = Optional.ofNullable(carTable.getItem(r -> r.key(userKey))).isPresent();
        LoggerService.info("[CarDao | Exists By carId] Result of existence check for car with id = {} is {}",
                carId, carExists);

        return carExists;
    }
}