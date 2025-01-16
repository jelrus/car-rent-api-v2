package com.car_rent_api.persistence.dao.components.impl;

import com.car_rent_api.config.Resources;
import com.car_rent_api.config.TableKeys;
import com.car_rent_api.persistence.dao.components.CarDao;
import com.car_rent_api.persistence.models.entity.Car;
import com.car_rent_api.persistence.pagination.api.TableRequest;
import com.car_rent_api.persistence.pagination.api.TableResponse;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;

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
    public Integer maxPrice() {
        Key carsKey = Key.builder().partitionValue(TableKeys.CAR_PK).sortValue(TableKeys.CAR_SK_PREFIX).build();
        QueryConditional carCondition = QueryConditional.sortBeginsWith(carsKey);
        QueryEnhancedRequest request = QueryEnhancedRequest.builder().queryConditional(carCondition).build();
        return carsVolume.query(request).items().stream().map(Car::getPricePerDay).max(Integer::compareTo).orElse(null);
    }

    @Override
    public Integer minPrice() {
        Key carsKey = Key.builder().partitionValue(TableKeys.CAR_PK).sortValue(TableKeys.CAR_SK_PREFIX).build();
        QueryConditional carCondition = QueryConditional.sortBeginsWith(carsKey);
        QueryEnhancedRequest request = QueryEnhancedRequest.builder().queryConditional(carCondition).build();
        return carsVolume.query(request).items().stream().map(Car::getPricePerDay).min(Integer::compareTo).orElse(null);
    }

    @Override
    public List<Car> findAll() {
        Key carKey = Key.builder()
                .partitionValue(TableKeys.CAR_PK)
                .sortValue(TableKeys.CAR_SK_PREFIX)
                .build();

        QueryConditional carCondition = QueryConditional.sortBeginsWith(carKey);

        return carsVolume.query(carCondition).stream().map(Page::items).flatMap(List::stream).toList();
    }

    @Override
    public TableResponse<Car> findByTableRequestIndexed(TableRequest tableRequest) {
        return TableResponse.<Car>builder().init(tableRequest).convertFromPages(findByTableRequest(tableRequest))
                .build();
    }

    @Override
    public TableResponse<Car> findByTableRequestIndexedPaginated(TableRequest tableRequest) {
        return TableResponse.<Car>builder().init(tableRequest).convertFromPages(findByTableRequest(tableRequest))
                .paginate().build();
    }

    private SdkIterable<Page<Car>> findByTableRequest(TableRequest tableRequest) {
        Key carsKey = Key.builder().partitionValue(TableKeys.CAR_PK).build();
        QueryConditional carCondition = QueryConditional.keyEqualTo(carsKey);

        QueryEnhancedRequest carRequest = QueryEnhancedRequest.builder()
                .queryConditional(carCondition)
                .scanIndexForward(tableRequest.getDirection())
                .filterExpression(tableRequest.getFilter())
                .build();

        return carsVolume.index(tableRequest.getSort()).query(carRequest);
    }
}