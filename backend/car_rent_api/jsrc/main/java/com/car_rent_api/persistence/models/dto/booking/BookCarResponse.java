package com.car_rent_api.persistence.models.dto.booking;

import com.car_rent_api.utils.components.LogPrinter;
import com.car_rent_api.utils.components.StringDateConverter;
import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
        public Builder constructMessage(String carModel, String pickupDateTime, String dropOffDateTime,
                                        String lockedFrom, String orderDetails) {
            LogPrinter.info("Constructing message...");
            DateTimeFormatter dateRangeFormatter = DateTimeFormatter.ofPattern("MMM dd").localizedBy(Locale.ENGLISH);
            LocalDateTime pickUp = StringDateConverter.fromStringToISO8601DateTime(pickupDateTime.replace(" ", "T"));
            LocalDateTime dropOff = StringDateConverter.fromStringToISO8601DateTime(dropOffDateTime.replace(" ", "T"));
            LogPrinter.info("Pick Up Date Time {}, Drop Off Date Time {}", pickUp, dropOff);

            pickupDateTime = pickUp.format(dateRangeFormatter);
            dropOffDateTime = dropOff.format(dateRangeFormatter);
            LogPrinter.info("Formatted Pick Up Date Time {}, Drop Off Date Time {}", pickupDateTime, dropOffDateTime);

            DateTimeFormatter lockedFromFormat = DateTimeFormatter.ofPattern("HH:mm a dd MMM")
                    .localizedBy(Locale.ENGLISH);
            LocalDateTime locked = StringDateConverter.fromStringToISO8601DateTime(lockedFrom.replace(" ", "T"));
            lockedFrom = locked.format(lockedFromFormat);
            LogPrinter.info("Formatted Locked from date time {}", lockedFrom);

            BookCarResponse.this.message =
                    " New booking was successfully created.\n" +
                    carModel + " is booked for " + pickupDateTime +  " - " + dropOffDateTime  + "\n" +
                    "You can change booking details until " + lockedFrom + ".\n" +
                    "Your order: " + orderDetails;
            return this;
        }

        public BookCarResponse build() {
            return BookCarResponse.this;
        }
    }
}