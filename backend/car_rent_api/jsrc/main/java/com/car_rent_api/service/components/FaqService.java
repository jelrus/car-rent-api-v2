package com.car_rent_api.service.components;

import com.car_rent_api.persistence.models.dto.faq.FaqResponse;

public interface FaqService {

    FaqResponse findAll();
}