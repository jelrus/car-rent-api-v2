package com.api.service.components.impl;

import com.api.persistence.dao.components.BookingDao;
import com.api.persistence.dao.components.CarDao;
import com.api.persistence.dao.components.FeedbackDao;
import com.api.persistence.dao.components.UserDao;
import com.api.persistence.models.dto.feedback.ClientReview;
import com.api.persistence.models.dto.feedback.ClientReviewSortedPageableResponse;
import com.api.persistence.models.dto.feedback.FeedbackInfo;
import com.api.persistence.models.dto.feedback.FeedbacksResponse;
import com.api.persistence.models.entity.Feedback;
import com.api.persistence.specification.FeedbackPageRequest;
import com.api.persistence.specification.FeedbackPageResponse;
import com.api.service.components.FeedbackService;
import com.api.utils.components.LogPrinter;

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
        return toFeedbacksResponse(feedbackDao.findFeedbacksSortedByRentalExperience());
    }

    @Override
    public ClientReviewSortedPageableResponse findFeedbacksFilteredByCarIdAndSorted(Map<String, String> params,
                                                                                    String carId) {
        LogPrinter.info("[FeedbackService] Entering findFeedbacksFilteredByCarIdAndSorted");
        FeedbackPageRequest feedbackPageRequest = FeedbackPageRequest.builder()
                .init(params).forSize().toPage().forSort().inDirection()
                .forCarId(carId)
                .build();

        LogPrinter.info("[FeedbackService] Page request {}", feedbackPageRequest.getFilter());
        return toClientReviewSortedPageableResponse(feedbackDao.findFeedbacksPaginatedAndFiltered(feedbackPageRequest));
    }

    private ClientReviewSortedPageableResponse toClientReviewSortedPageableResponse(
            FeedbackPageResponse feedbackPageResponse) {
        LogPrinter.info("[FeedbackService] Converting ClientReviewSortedPageableResponse");
        return ClientReviewSortedPageableResponse.builder()
                .content(feedbackPageResponse.getFeedbacks().stream().map(toClientReview()).toList())
                .elementsOnPage(feedbackPageResponse.getElementsOnPage())
                .totalElements(feedbackPageResponse.getTotalElements())
                .currentPage(feedbackPageResponse.getCurrentPage())
                .totalPages(feedbackPageResponse.getTotalPages())
                .build();
    }

    private Function<Feedback, FeedbackInfo> toFeedbackInfo() {
        LogPrinter.info("[FeedbackService] Converting Feedback to FeedbackInfo");
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
        LogPrinter.info("[FeedbackService] Converting to FeedbacksResponse");
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