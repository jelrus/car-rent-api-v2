package com.backend.service.impl;

import com.backend.dao.CarDao;
import com.backend.exception.CarNotFoundException;
import com.backend.models.table.CarEntity;
import com.backend.service.CarService;
import com.backend.utils.services.LoggerService;
import com.google.gson.Gson;

public class CarServiceImpl implements CarService {

   private CarDao carDao;
   private Gson gson;

   public CarServiceImpl(CarDao carDao, Gson gson) {
      this.carDao = carDao;
      this.gson = gson;
   }

   @Override
   public CarEntity findByCarId(String carId) {
      LoggerService.info("[CarService | findByCarId] Finding car by carId=  {}", gson.toJson(carId));
      CarEntity response = carDao.findByCarId(carId);
      if (response == null) {
         LoggerService.warn("[CarService | findByCarId] Car was not found in table");
         throw new CarNotFoundException("Car not found");
      }
      return response;
   }

   @Override
   public void existsByCarId(String carId) {
      if (!carDao.existsByCarId(carId)) {
         LoggerService.error("[CarService | Exists By carId] Car is not found in the table");
         throw new CarNotFoundException("Car not found");
      }
      LoggerService.info("[CarService | Exists By carId] Car exists in table");
   }
}
