package com.car_rent_api.persistence.models.dto.booking;

import com.car_rent_api.persistence.models.entity.Booking;
import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.StringDateConverter;
import com.google.gson.annotations.Expose;

public class BookCarResponse {

    @Expose
    private String message;

    public BookCarResponse() {
    }

    public static Builder builder() {
        return new BookCarResponse().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        /*
            car#model/Audi A6 Quattro 2023
            booking#pickupDateTime/[MMM dd] Nov 11
            booking#dropOffDateTime/[MMM dd] Nov 16
            booking#lockDate/10:30 PM 10 Nov
            booking#totalOrders => booking find all
            booking#createdAt => [dd.MM.yy] 08.06.24
            booking#orderDetails [#{booking#totalOrders} ({booking#createdAt})]

            New booking was successfully created. \n
            {car#model} is booked for {booking#pickupDateTime} - {booking#dropOffDateTime} \n
            You can change booking details until {booking#lockDate}.\n
            Your order: {booking#orderDetails}

            New booking was successfully created. \n
            Audi A6 Quattro 2023 is booked for Nov 11 - Nov 16 \n
            You can change booking details until 10:30 PM 10 Nov.\n
            Your order: #2437 (08.06.24)
         */
        public Builder constructCreateMessage(String carModel, Booking booking) {
            LogPrinter.info("Constructing create message...");
            String pickUp = StringDateConverter.fromISO8601DateTimeToBookingActiveDate(booking.getPickupDateTime());
            String dropOff = StringDateConverter.fromISO8601DateTimeToBookingActiveDate(booking.getDropOffDateTime());
            String locked = StringDateConverter.fromISO8601DateTimeToLockedFromDateTime(booking.getLockedFrom());

            BookCarResponse.this.message =
                    " New booking was successfully created.\n" +
                            carModel + " is booked for " + pickUp + " - " + dropOff + "\n" +
                            "You can change booking details until " + locked + ".\n" +
                            "Your order: " + booking.getOrderDetails();
            return this;
        }

        public Builder constructEditMessage(String carModel, Booking booking) {
            LogPrinter.info("Constructing edit message...");
            String pickUp = StringDateConverter.fromISO8601DateTimeToBookingActiveDate(booking.getPickupDateTime());
            String dropOff = StringDateConverter.fromISO8601DateTimeToBookingActiveDate(booking.getDropOffDateTime());
            String locked = StringDateConverter.fromISO8601DateTimeToLockedFromDateTime(booking.getLockedFrom());

            BookCarResponse.this.message =
                    "Booking was successfully updated. \n" +
                            carModel + " is now booked for " + pickUp + " - " + dropOff + "\n" +
                            "You can change booking details until " + locked + ".\n" +
                            "Your order: " + booking.getOrderDetails();
            return this;
        }

        public Builder constructCancelMessage(String carModel, Booking booking) {
            LogPrinter.info("Constructing edit message...");
            String pickUp = StringDateConverter.fromISO8601DateTimeToBookingActiveDate(booking.getPickupDateTime());
            String dropOff = StringDateConverter.fromISO8601DateTimeToBookingActiveDate(booking.getDropOffDateTime());

            BookCarResponse.this.message =
                    "Your order: " + booking.getOrderDetails() + ".\n" +
                            "For " + carModel + ".\n" +
                            "On " + pickUp + " - " + dropOff + " has been successfully cancelled.";
            return this;
        }

        public Builder constructServiceStartedMessage(String carModel, Booking booking) {
            LogPrinter.info("Constructing service started message...");
            String pickUp = StringDateConverter.fromISO8601DateTimeToBookingActiveDate(booking.getPickupDateTime());
            String dropOff = StringDateConverter.fromISO8601DateTimeToBookingActiveDate(booking.getDropOffDateTime());

            BookCarResponse.this.message =
                    "Order: " + booking.getOrderDetails() + ".\n" +
                            "For " + carModel + ".\n" +
                            "On " + pickUp + " - " + dropOff + "has just started.";
            return this;
        }

        public Builder constructServiceProvidedMessage(String carModel, Booking booking) {
            LogPrinter.info("Constructing service provided message...");
            String pickUp = StringDateConverter.fromISO8601DateTimeToBookingActiveDate(booking.getPickupDateTime());
            String dropOff = StringDateConverter.fromISO8601DateTimeToBookingActiveDate(booking.getDropOffDateTime());

            BookCarResponse.this.message =
                    "Order: " + booking.getOrderDetails() + ".\n" +
                            "For " + carModel + ".\n" +
                            "On " + pickUp + " - " + dropOff + " has just ended.";
            return this;
        }

        public BookCarResponse build() {
            return BookCarResponse.this;
        }
    }
}