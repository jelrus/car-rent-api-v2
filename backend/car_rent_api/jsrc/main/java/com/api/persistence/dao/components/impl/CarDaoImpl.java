package com.api.persistence.dao.components.impl;

import com.api.config.Resources;
import com.api.config.TableKeys;
import com.api.persistence.dao.components.CarDao;
import com.api.persistence.models.entity.Car;
import com.api.persistence.specification.CarPageRequest;
import com.api.persistence.specification.CarPageResponse;
import com.api.persistence.specification.PaginationBuilder;
import com.api.utils.components.LogPrinter;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.Select;

import java.util.List;

public class CarDaoImpl implements CarDao {

    private final DynamoDbTable<Car> carsVolume;

    public CarDaoImpl(DynamoDbEnhancedClient dbClient) {
        this.carsVolume = dbClient.table(Resources.DYNAMO_DATABASE, TableSchema.fromClass(Car.class));
    }

    @Override
    public Car put(Car car) {
        PutItemEnhancedRequest<Car> carRequest = PutItemEnhancedRequest.builder(Car.class).item(car).build();
        carsVolume.putItem(carRequest);
        return car;
    }

    @Override
    public Car findById(String id) {
        Key carKey = Key.builder().partitionValue(TableKeys.CAR_PK).sortValue(TableKeys.CAR_SK_PREFIX + id).build();
        GetItemEnhancedRequest carRequest = GetItemEnhancedRequest.builder().key(carKey).build();
        return carsVolume.getItem(carRequest);
    }

    @Override
    public Boolean isExistsById(String id) {
        Key carKey = Key.builder().partitionValue(TableKeys.CAR_PK).sortValue(TableKeys.CAR_SK_PREFIX + id).build();
        GetItemEnhancedRequest carRequest = GetItemEnhancedRequest.builder().key(carKey).build();
        return carsVolume.getItem(carRequest) != null;
    }

    @Override
    public Boolean isBookedDatesAreFree(CarPageRequest carPageRequest) {
        Key carKey = Key.builder().partitionValue(TableKeys.CAR_PK)
                .sortValue(TableKeys.CAR_SK_PREFIX + carPageRequest.getParams().get("id"))
                .build();

        QueryConditional carCondition = QueryConditional.keyEqualTo(carKey);
        QueryEnhancedRequest carRequest = QueryEnhancedRequest.builder()
                .queryConditional(carCondition)
                .filterExpression(carPageRequest.getFilter())
                .build();

        return !carsVolume.query(carRequest).stream()
                .map(Page::items)
                .flatMap(List::stream)
                .toList()
                .isEmpty();
    }

    @Override
    public CarPageResponse findCarsByCategorySortedByRentalExperience(CarPageRequest carPageRequest) {
        LogPrinter.info("[CarDao | Find Cars By Category and Sorted By Rental Experience] Entering CarDao " +
                "findCarsByCategorySortedByRentalExperience() {}");
        Key carsKey = Key.builder().partitionValue(TableKeys.CAR_PK).build();
        QueryConditional carCondition = QueryConditional.keyEqualTo(carsKey);
        QueryEnhancedRequest carRequest = QueryEnhancedRequest.builder()
                .queryConditional(carCondition)
                .select(Select.ALL_ATTRIBUTES)
                .scanIndexForward(false)
                .filterExpression(carPageRequest.getFilter())
                .build();

        LogPrinter.info("[CarDao | Find Cars By Category and Sorted By Rental Experience] Querying...");
        List<Car> cars = carsVolume.index(TableKeys.CAR_RENTAL_EXPERIENCE_IDX).query(carRequest).stream()
                .map(Page::items)
                .flatMap(List::stream)
                .toList();

        return CarPageResponse.builder().items(cars).build();
    }

    @Override
    public CarPageResponse findCarsFiltered(CarPageRequest carPageRequest) {
        LogPrinter.info("[CarDao | Find Cars Filtered] Entering CarDao findCarsFiltered() {}");
        Key carsKey = Key.builder().partitionValue(TableKeys.CAR_PK).build();
        QueryConditional carCondition = QueryConditional.keyEqualTo(carsKey);
        QueryEnhancedRequest carRequest = QueryEnhancedRequest.builder()
                .queryConditional(carCondition)
                .filterExpression(carPageRequest.getFilter())
                .build();

        LogPrinter.info("[CarDao | Find Cars Filtered] Querying...");
        SdkIterable<Page<Car>> carPages = carsVolume.index(TableKeys.CAR_STATUS_IDX).query(carRequest);

        LogPrinter.info("[CarDao | Find Cars Filtered] Entering pagination for cars filtering");
        PaginationBuilder<Car> carPageBuilder =
                new PaginationBuilder<>(carPageRequest.getPage(), carPageRequest.getSize());
        carPageBuilder.paginate(carPages);

        return CarPageResponse.builder()
                .items(carPageBuilder.getItems())
                .currentPage(carPageBuilder.getPage())
                .totalPages(carPageBuilder.getTotalPages())
                .elementsOnPage(carPageBuilder.getElementsOnPage())
                .totalElements(carPageBuilder.getTotalElements())
                .build();
    }
}