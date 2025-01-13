package com.car_rent_api.persistence.dao.components;

import com.car_rent_api.persistence.models.entity.Booking;
import com.car_rent_api.persistence.pagination.api.TableRequest;
import com.car_rent_api.persistence.pagination.api.TableResponse;

public interface BookingDao {

    Booking put(Booking booking);

    Booking findById(String id);

    Boolean isExistsById(String id);

    Integer getTotalCount();

    TableResponse<Booking> findByTableRequestIndexed(TableRequest tableRequest);
}