package com.car_rent_api.service.components.impl;

import com.car_rent_api.config.TableKeys;
import com.car_rent_api.persistence.dao.components.BookingDao;
import com.car_rent_api.persistence.dao.components.CarDao;
import com.car_rent_api.persistence.dao.components.FeedbackDao;
import com.car_rent_api.persistence.dao.components.UserDao;
import com.car_rent_api.persistence.models.dto.feedback.ClientReview;
import com.car_rent_api.persistence.models.dto.feedback.ClientReviewSortedPageableResponse;
import com.car_rent_api.persistence.models.dto.feedback.FeedbackInfo;
import com.car_rent_api.persistence.models.dto.feedback.FeedbacksResponse;
import com.car_rent_api.persistence.models.entity.Feedback;
import com.car_rent_api.persistence.pagination.api.TableRequest;
import com.car_rent_api.persistence.pagination.api.TableResponse;
import com.car_rent_api.persistence.pagination.api.PaginationRequest;
import com.car_rent_api.persistence.pagination.api.SpecificationRequest;
import com.car_rent_api.persistence.pagination.type.JoinType;
import com.car_rent_api.persistence.pagination.type.ValueType;
import com.car_rent_api.service.components.FeedbackService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackDao feedbackDao;
    private final UserDao userDao;
    private final CarDao carDao;
    private final BookingDao bookingDao;

    public FeedbackServiceImpl(FeedbackDao feedbackDao, UserDao userDao, CarDao carDao, BookingDao bookingDao) {
        this.feedbackDao = feedbackDao;
        this.userDao = userDao;
        this.carDao = carDao;
        this.bookingDao = bookingDao;
    }

    @Override
    public FeedbacksResponse findFeedbacksSortedByRentalExperience() {
        TableRequest tableRequest = TableRequest.builder()
                .pagination(PaginationRequest.builder()
                        .defaultSort(TableKeys.FEEDBACK_RENTAL_EXPERIENCE_IDX)
                        .defaultDirection(false)
                        .build())
                .build();

        return toFeedbacksResponse(feedbackDao.findByTableRequestIndexed(tableRequest).getItems());
    }

    @Override
    public ClientReviewSortedPageableResponse findAllByCarIdDateSortedDesc(Map<String, String> params, String carId) {
        Map<String, String> sortIndexes =
                Map.of("date", TableKeys.FEEDBACK_DATE_IDX, "rating", TableKeys.FEEDBACK_RENTAL_EXPERIENCE_IDX);

        TableRequest tableRequest = TableRequest.builder()
                .pagination(PaginationRequest.builder()
                        .page(params.get("page"), 1)
                        .size(params.get("size"), 4)
                        .indexes(sortIndexes)
                        .sort(params.get("sort"), TableKeys.FEEDBACK_DATE_IDX)
                        .direction(params.get("direction"), false)
                        .build())
                .specification(SpecificationRequest.builder()
                        .equalTo(ValueType.STRING, "FEEDBACK#CAR_ID", carId)
                        .build(JoinType.AND))
                .build();

        return toClientReviewSortedPageableResponse(feedbackDao.findByTableRequestIndexedPaginated(tableRequest));
    }

    private ClientReviewSortedPageableResponse toClientReviewSortedPageableResponse(
            TableResponse<Feedback> tableResponse
    ) {
        return ClientReviewSortedPageableResponse.builder()
                .content(tableResponse.getItems().stream().map(toClientReview()).toList())
                .elementsOnPage(tableResponse.getElementsOnPage())
                .totalElements(tableResponse.getTotalElements())
                .currentPage(tableResponse.getPage())
                .totalPages(tableResponse.getTotalPages())
                .build();
    }

    private Function<Feedback, FeedbackInfo> toFeedbackInfo() {
        return s ->  FeedbackInfo.builder()
                .feedbackId(s.getSkId())
                .author(userDao.findById(s.getClientId()).getUsername())
                .rentalExperience(s.getRentalExperience())
                .carModel(carDao.findById(s.getCarId()).getModel())
                .carImageUrl(carDao.findById(s.getCarId()).getImageUrl())
                .date(s.getDate())
                .orderHistory(bookingDao.findById(s.getBookingId()).getOrderDetails())
                .feedbackText(s.getText())
                .build();
    }

    private FeedbacksResponse toFeedbacksResponse(List<Feedback> feedbacks) {
        return FeedbacksResponse.builder().content(feedbacks.stream().map(toFeedbackInfo()).toList()).build();
    }

    private Function<Feedback, ClientReview> toClientReview() {
        return s -> ClientReview.builder()
                .author(userDao.findById(s.getClientId()).getUsername())
                .authorImageUrl(userDao.findById(s.getClientId()).getImageUrl())
                .date(s.getDate())
                .rentalExperience(s.getRentalExperience())
                .text(s.getText())
                .build();
    }
}