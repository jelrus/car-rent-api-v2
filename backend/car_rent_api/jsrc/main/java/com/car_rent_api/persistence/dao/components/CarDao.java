package com.car_rent_api.persistence.dao.components;

import com.car_rent_api.persistence.models.entity.Car;
import com.car_rent_api.persistence.pagination.api.TableRequest;
import com.car_rent_api.persistence.pagination.api.TableResponse;

import java.util.List;

public interface CarDao {

    Car put(Car car);

    Car findById(String id);

    Boolean isExistsById(String id);

    Integer maxPrice();

    Integer minPrice();

    List<Car> findAll();

    TableResponse<Car> findByTableRequestIndexed(TableRequest tableRequest);

    TableResponse<Car> findByTableRequestIndexedPaginated(TableRequest tableRequest);
}