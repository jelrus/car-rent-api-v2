package com.car_rent_api.persistence.dao.components;

import com.car_rent_api.persistence.models.entity.Feedback;
import com.car_rent_api.persistence.pagination.api.TableRequest;
import com.car_rent_api.persistence.pagination.api.TableResponse;

public interface FeedbackDao {

    Feedback put(Feedback feedback);

    Feedback findByBookingId(String bookingId);

    Double calculateAverageRating(String carId);

    TableResponse<Feedback> findByTableRequestIndexed(TableRequest tableRequest);

    TableResponse<Feedback> findByTableRequestIndexedPaginated(TableRequest feedbackTableRequest);
}