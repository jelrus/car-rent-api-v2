package com.car_rent_api.service.components.impl;

import com.car_rent_api.config.TableKeys;
import com.car_rent_api.exception.ExistenceException;
import com.car_rent_api.persistence.dao.components.BookingDao;
import com.car_rent_api.persistence.dao.components.CarDao;
import com.car_rent_api.persistence.dao.components.FeedbackDao;
import com.car_rent_api.persistence.dao.components.UserDao;
import com.car_rent_api.persistence.models.dto.feedback.*;
import com.car_rent_api.persistence.models.entity.Booking;
import com.car_rent_api.persistence.models.entity.Feedback;
import com.car_rent_api.persistence.models.entity.types.BookingStatus;
import com.car_rent_api.persistence.specification.FeedbackPageRequest;
import com.car_rent_api.persistence.specification.FeedbackPageResponse;
import com.car_rent_api.service.components.FeedbackService;
import com.car_rent_api.utils.components.LogPrinter;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import static com.car_rent_api.utils.components.StringDateConverter.generateCurrentGermanDate;

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

    @Override
    public CreateFeedbacksResponse create(CreateFeedbackRequest request) {
        LogPrinter.warn("[FeedbackService | Create] Entering create() method");

        LogPrinter.warn("[FeedbackService | Create] Checking booking existence");
        checkBookingExistence(request);

        LogPrinter.warn("[FeedbackService | Create] Generating feedback");
        Feedback feedback = toFeedback(request);

        LogPrinter.warn("[FeedbackService | Create] Putting feedback to DB");
        feedbackDao.put(feedback);
        LogPrinter.warn("[FeedbackService | Create] Feedback {} was successfully created", feedback.getSkId());

        return CreateFeedbacksResponse.builder()
                .feedbackId(feedback.getSkId().replace(TableKeys.FEEDBACK_SK_PREFIX, ""))
                .systemMessage("Feedback has been successfully created")
                .build();
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

    private Feedback toFeedback(CreateFeedbackRequest request) {
        LogPrinter.info("[FeedbackService] Converting CreateFeedbackRequest to Feedback");
        return Feedback.builder()
                .pkId()
                .skId(UUID.randomUUID().toString())
                .bookingId(request.getBookingId())
                .carId(request.getCarId())
                .clientId(request.getClientId())
                .text(request.getFeedbackText())
                .rentalExperience(request.getRating())
                .date(generateCurrentGermanDate())
                .build();
    }

    private void checkBookingExistence(CreateFeedbackRequest request) {
        if (request == null || request.getBookingId() == null) {
            LogPrinter.error("FeedbackRequest does not exist");
            throw new ExistenceException("FeedbackRequest does not exist");
        }

        Booking booking = bookingDao.findById(request.getBookingId());
        if (booking == null) {
            LogPrinter.error("Booking with id {}", TableKeys.BOOKING_SK_PREFIX + request.getBookingId() + " does not exist");
            throw new ExistenceException("Booking does not exist");
        }

        if (!booking.getStatus().equals(BookingStatus.SERVICE_PROVIDED)) {
            LogPrinter.error("Booking has not provided completely. Status is not 'Service Provided'");
            throw new ExistenceException("Booking has not provided completely. Status is not 'Service Provided'");
        }

        if (!booking.getCarId().equals(request.getCarId())) {
            LogPrinter.error("Provided carId does not match the booking carId");
            throw new ExistenceException("Provided carId does not match the booking carId");
        }

        if (!booking.getClientId().equals(request.getClientId())) {
            LogPrinter.error("Provided clientId does not match the booking clientId");
            throw new ExistenceException("Provided clientId does not match the booking clientId");
        }
    }
}