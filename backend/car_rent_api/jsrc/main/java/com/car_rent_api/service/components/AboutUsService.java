package com.car_rent_api.service.components;

import com.car_rent_api.persistence.models.dto.about_us.AboutUsResponse;

public interface AboutUsService {

    AboutUsResponse findAll();
}