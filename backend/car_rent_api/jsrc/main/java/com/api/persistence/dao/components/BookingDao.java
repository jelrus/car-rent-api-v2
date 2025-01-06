package com.api.persistence.dao.components;

import com.api.persistence.models.entity.Booking;

import java.util.List;

public interface BookingDao {

    Booking put(Booking booking);

    Booking findById(String id);

    Boolean isExistsById(String id);

    Integer getTotalCount();

    List<Booking> findAllByClientIdSortedByCreatedAt(String clientId);
}