package com.backend.service.impl;

import com.backend.dao.CarDao;
import com.backend.exception.CarNotAvailableException;
import com.backend.exception.CarNotFoundException;
import com.backend.models.dto.request.BookCarRequest;
import com.backend.models.table.Car;
import com.backend.service.BookingService;
import com.backend.service.CarService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.List;

import static com.backend.utils.services.CustomDateTimeFormatter.convertDateTimeToDate;

/**
 * Service implementation for managing car-related operations.
 * This includes retrieving car details, checking existence, and verifying availability of cars.
 */
public class CarServiceImpl implements CarService {

   private final BookingService bookingService;
   private final CarDao carDao;
   private final Gson gson;

   public CarServiceImpl(BookingService bookingService, CarDao carDao, Gson gson) {
      this.bookingService = bookingService;
      this.carDao = carDao;
      this.gson = gson;
   }

   /**
    * Retrieves a CarEntity by its ID.
    *
    * @param carId The ID of the car to retrieve.
    * @return The CarEntity associated with the provided ID.
    * @throws CarNotFoundException if no car is found with the specified ID.
    */
   @Override
   public Car findByCarId(String carId) {
      LoggerService.info("[CarService | findByCarId] Finding car by carId=  {}", gson.toJson(carId));
      Car response = carDao.findByCarId(carId);
      if (response == null) {
         LoggerService.warn("[CarService | findByCarId] Car was not found in table");
         throw new CarNotFoundException("Car not found");
      }
      return response;
   }

   /**
    * Checks if a car exists in the database by its ID.
    *
    * @param carId The ID of the car to check.
    * @throws CarNotFoundException if the car with the specified ID does not exist.
    */
   @Override
   public void existsByCarId(String carId) {
      if (!carDao.existsByCarId(carId)) {
         LoggerService.error("[CarService | Exists By carId] Car is not found in the table");
         throw new CarNotFoundException("Car not found");
      }
      LoggerService.info("[CarService | Exists By carId] Car exists in table");
   }

   /**
    * Checks if a car is available for booking within a specified date range.
    *
    * @param request The booking request containing the car ID and date range.
    * @throws CarNotAvailableException if the car is not available for the entire requested date range.
    */
   @Override
   public void checkCarAvailable(BookCarRequest request){

      // retrieve all booked dates for the car
      List<String> bookedDates = bookingService.getCarBookedDates(request.getCarId());

      // parse the start and end dates from the request
      LocalDate endDate = LocalDate.parse(convertDateTimeToDate(request.getDropOffDateTime()));
      LocalDate startDate = LocalDate.parse(convertDateTimeToDate(request.getPickupDateTime()));

      // check if all booked dates fall outside the requested date range
      boolean isAvailable = bookedDates.stream()
              .allMatch(d -> LocalDate.parse(d).isBefore(startDate) || LocalDate.parse(d).isAfter(endDate));
      LoggerService.info("[CarService | checkCarAvailable] Car available: {}", isAvailable);

      if (!isAvailable) {
         throw new CarNotAvailableException("The car is not available.");
      }
   }
}
