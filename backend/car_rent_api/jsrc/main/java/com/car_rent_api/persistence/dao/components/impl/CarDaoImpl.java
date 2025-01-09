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
    public Boolean isBookedDatesAreFree(String id, TableRequest carTableRequest) {
        Key carKey = Key.builder()
                .partitionValue(TableKeys.CAR_PK)
                .sortValue(TableKeys.CAR_SK_PREFIX + id)
                .build();

        QueryConditional carCondition = QueryConditional.keyEqualTo(carKey);

        QueryEnhancedRequest carRequest = QueryEnhancedRequest.builder()
                .queryConditional(carCondition)
                .filterExpression(carTableRequest.getFilter())
                .build();

        return !TableResponse.<Car>builder().convertFromPages(carsVolume.query(carRequest)).build().getItems().isEmpty();
    }

    @Override
    public TableResponse<Car> findByTableRequestIndexed(TableRequest tableRequest) {
        Key carsKey = Key.builder().partitionValue(TableKeys.CAR_PK).build();

        QueryConditional carCondition = QueryConditional.keyEqualTo(carsKey);
        QueryEnhancedRequest carRequest = QueryEnhancedRequest.builder()
                .queryConditional(carCondition)
                .scanIndexForward(tableRequest.getDirection())
                .filterExpression(tableRequest.getFilter())
                .build();

        SdkIterable<Page<Car>> cars = carsVolume.index(tableRequest.getSort()).query(carRequest);

        return TableResponse.<Car>builder().init(tableRequest).convertFromPages(cars).build();
    }

    @Override
    public TableResponse<Car> findByTableRequestIndexedPaginated(TableRequest tableRequest) {
        Key carsKey = Key.builder().partitionValue(TableKeys.CAR_PK).build();
        QueryConditional carCondition = QueryConditional.keyEqualTo(carsKey);

        QueryEnhancedRequest carRequest = QueryEnhancedRequest.builder()
                .queryConditional(carCondition)
                .scanIndexForward(tableRequest.getDirection())
                .filterExpression(tableRequest.getFilter())
                .build();

        SdkIterable<Page<Car>> cars = carsVolume.index(tableRequest.getSort()).query(carRequest);

        return TableResponse.<Car>builder().init(tableRequest).convertFromPages(cars).paginate().build();
    }
}