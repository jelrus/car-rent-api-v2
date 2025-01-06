package com.api.service.components;

import com.api.persistence.models.dto.faq.FaqResponse;

public interface FaqService {

    FaqResponse findAll();
}