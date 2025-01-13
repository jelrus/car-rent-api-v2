package com.car_rent_api.utils.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StringDateConverter {

    private static final String GERMAN_DATE = "dd.MM.yyyy"; //29.10.2024
    private static final String ISO8601_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss"; //2024-10-29T10:30:00
    private static final String BOOKING_DATE_TIME_CREATED_AT = "dd.MM.yy";
    private static final String BOOKING_ACTIVE_DATE = "MMM dd";
    private static final String BOOKING_LOCKED_FROM_DATE_TIME = "HH:mm a dd MMM";

    public static List<String> generateGermanDatesRange(String pickupDateTime, String dropOffDateTime) {
        if (isRangeCandidates(pickupDateTime, dropOffDateTime)) {
            List<String> datesRange = new ArrayList<>();
            LocalDate dateA = fromISO8601DateTimeToDate(pickupDateTime);
            LogPrinter.info("Start Date {}", dateA.toString());
            LocalDate dateB = fromISO8601DateTimeToDate(dropOffDateTime);
            LogPrinter.info("End Date {}", dateB.toString());

            if (isISO8601DateTimeStartEqualsEnd(pickupDateTime, dropOffDateTime)) {
                datesRange.add(fromISO8601ToGermanDateString(dateA));
            }

            if (isISO8601DateTimeStartBeforeEnd(pickupDateTime, dropOffDateTime)) {
                while (dateA.isBefore(dateB)) {
                    datesRange.add(fromISO8601ToGermanDateString(dateA));
                    dateA = dateA.plusDays(1);
                    LogPrinter.info("Start Date {}, End Date {}", dateA.toString(), dateB.toString());
                }

                datesRange.add(fromISO8601ToGermanDateString(dateB));
                LogPrinter.info("Dates range {}", datesRange.toString());
            }

            return datesRange;
        } else {
            return List.of();
        }
    }

    public static LocalDateTime fromStringToISO8601DateTime(String dateTime) {
        return LocalDateTime.parse(LocalDateTime.parse(dateTime)
                .format(DateTimeFormatter.ofPattern(ISO8601_DATE_TIME)));
    }

    public static LocalDate fromISO8601DateTimeToDate(String dateTime) {
        return fromStringToISO8601DateTime(dateTime).toLocalDate();
    }

    public static String fromISO8601ToGermanDateString(LocalDate iso8601Date) {
        return iso8601Date.format(DateTimeFormatter.ofPattern(GERMAN_DATE));
    }

    public static String fromLocalDateTimeToBookingCreatedAt() {
        return LocalDateTime.now(ZoneId.of("Europe/Kiev"))
                .format(DateTimeFormatter.ofPattern(BOOKING_DATE_TIME_CREATED_AT));
    }

    public static String getLockedDateTime(String iso8601DateTime) {
        return LocalDateTime.parse(iso8601DateTime).minusHours(12)
                .format(DateTimeFormatter.ofPattern(ISO8601_DATE_TIME));
    }

    public static String fromISO8601DateTimeToBookingActiveDate(String iso8601DateTime) {
        return LocalDateTime.parse(iso8601DateTime)
                .format(DateTimeFormatter.ofPattern(BOOKING_ACTIVE_DATE).localizedBy(Locale.ENGLISH));
    }

    public static String fromISO8601DateTimeToLockedFromDateTime(String iso8601DateTime) {
        return LocalDateTime.parse(iso8601DateTime).format(DateTimeFormatter.ofPattern(BOOKING_LOCKED_FROM_DATE_TIME));
    }

    public static String toISO8601DateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(ISO8601_DATE_TIME));
    }

    public static String adjustToISO8601DateTime(String localDateTime) {
        return LocalDateTime.parse(localDateTime.replace(" ", "T"))
                .format(DateTimeFormatter.ofPattern(ISO8601_DATE_TIME));
    }

    public static String toISO8601DateTimeRounded(LocalDateTime localDateTime) {
        int minutes = localDateTime.getMinute();
        int minutesToAdd = (minutes % 30 == 0) ? 0 : 30 - (minutes % 30);
        return toISO8601DateTime(localDateTime.plusMinutes(minutesToAdd).withSecond(0).withNano(0));
    }

    public static boolean isISO8601DateTime(String value) {
        try {
            LocalDateTime.parse(value).format(DateTimeFormatter.ofPattern(ISO8601_DATE_TIME));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isSourceTimeAfterTargetTime(String dateTimeSource, String dateTimeTarget) {
        LocalDateTime source = fromStringToISO8601DateTime(dateTimeSource);
        LocalDateTime target = fromStringToISO8601DateTime(dateTimeTarget);
        return source.isAfter(target);
    }

    public static boolean isTargetTimeBeforeCurrentTime(String dateTimeTarget) {
        LocalDateTime current = LocalDateTime.now(ZoneId.of("Europe/Kiev"));
        LocalDateTime target = fromStringToISO8601DateTime(dateTimeTarget);
        return target.isBefore(current);
    }

    public static boolean isCurrentTimeAfterOrEqualDateTime(String dateTime) {
        LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Europe/Kiev"));
        LocalDateTime checkedTime = fromStringToISO8601DateTime(dateTime);
        return currentTime.isEqual(checkedTime) || currentTime.isAfter(checkedTime);
    }

    public static boolean isISO8601DateTimeStartBeforeEnd(String start, String end) {
        return fromStringToISO8601DateTime(start).isBefore(fromStringToISO8601DateTime(end));
    }

    public static boolean isISO8601DateTimeStartEqualsEnd(String start, String end) {
        return fromStringToISO8601DateTime(start).isEqual(fromStringToISO8601DateTime(end));
    }

    public static boolean isRangeCandidates(String pickupDateTime, String dropOffDateTime) {
        return pickupDateTime != null && dropOffDateTime != null &&
                isISO8601DateTime(pickupDateTime) && isISO8601DateTime(dropOffDateTime);
    }

    public static String generateCurrentGermanDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(GERMAN_DATE));
    }
}