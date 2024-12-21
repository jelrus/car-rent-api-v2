package com.backend.dao.impl;

import com.backend.dao.PopularCarsDao;
import com.backend.models.table.CarEntity;
import com.backend.models.table.types.CarCategory;
import com.backend.models.table.types.CarStatus;
import com.backend.utils.components.Envs;
import com.backend.utils.services.LoggerService;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PopularCarsDaoImpl implements PopularCarsDao {

    private final DynamoDbTable<CarEntity> carTable;

    public PopularCarsDaoImpl(DynamoDbEnhancedClient dbEnhancedClient) {
        this.carTable = dbEnhancedClient.table(Envs.CARS_TABLE, TableSchema.fromClass(CarEntity.class));
        for (int i = 0; i < 10; i++) {
            create(generateRandomCarEntity());
        }
    }

    @Override
    public CarEntity create(CarEntity car) {
        LoggerService.warn("[PopularCarsDao|Create] Entered method with Car {}", car.toString());
        LoggerService.warn("[PopularCarsDao|Create] Attempt to create requested car");
        try {
            PutItemEnhancedRequest<CarEntity> carItem = PutItemEnhancedRequest.builder(CarEntity.class).item(car).build();
            carTable.putItem(carItem);
        } catch (Exception e) {
            LoggerService.warn(e.getMessage());
        }
        LoggerService.info("[PopularCarsDao|Create] Car has been successfully created");
        LoggerService.warn("[PopularCarsDao|Create] Exiting method");
        return car;
    }


    @Override
    public List<CarEntity> findAllByCategory(CarCategory carCategory) {

        // Create an Expression to filter only items where category is carCategory
        Expression filterExpression = Expression.builder()
                .expression("category = :category")
                .putExpressionValue(":category", AttributeValue.builder().s(carCategory.getCategory()).build())
                .build();

        // Create a ScanEnhancedRequest with the filter expression
        ScanEnhancedRequest scanRequest = ScanEnhancedRequest.builder()
                .filterExpression(filterExpression)
                .build();

        PageIterable<CarEntity> scan = carTable.scan(scanRequest);

        List<CarEntity> cars = scan.items().stream().collect(Collectors.toList());

        LoggerService.warn("Total record car " + cars.size() + " in category " + carCategory.getCategory());

        return cars;
    }

    private CarEntity generateRandomCarEntity() {
        CarEntity carBriefInfo = new CarEntity();
        carBriefInfo.setCarId(UUID.randomUUID().toString());
        carBriefInfo.setCarRating(String.valueOf((int) (Math.random() * (4 - 1) + 1) + 0.7));
        carBriefInfo.setImageUrl("https://application.s3.eu-central-1.amazonaws.com/img/cars/audi-A6-quattro-2023.jpg");
        carBriefInfo.setLocation("Ukraine, Kyiv");
        carBriefInfo.setModel("Audi A6 Quattro 2023");
        carBriefInfo.setPricePerDay(String.valueOf((int) (Math.random() * (4 - 1) + 1) + 0.5));
        carBriefInfo.setServiceRating(String.valueOf((int) (Math.random() * (4 - 1) + 1) + 0.6));
        carBriefInfo.setStatus(CarStatus.AVAILABLE.getStatus());
        carBriefInfo.setCategory(Double.parseDouble(carBriefInfo.getPricePerDay()) > 2
                ? CarCategory.BUSINESS.getCategory()
                : CarCategory.ECONOMY.getCategory());
        return carBriefInfo;
    }
}