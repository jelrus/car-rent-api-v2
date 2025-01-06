package com.car_rent_api.service.components.impl;

import com.car_rent_api.persistence.dao.components.FaqDao;
import com.car_rent_api.persistence.models.dto.faq.FaqResponse;
import com.car_rent_api.persistence.models.dto.faq.FaqStoryInfo;
import com.car_rent_api.persistence.models.entity.FaqStory;
import com.car_rent_api.service.components.FaqService;
import com.car_rent_api.utils.components.LogPrinter;

import java.util.List;
import java.util.function.Function;

public class FaqServiceImpl implements FaqService {

    private final FaqDao faqDao;

    public FaqServiceImpl(FaqDao faqDao) {
        this.faqDao = faqDao;
    }

    @Override
    public FaqResponse findAll() {
        LogPrinter.warn("[FaqService | Find All] Entering 'findAll @ FaqService' method");
        FaqResponse faqResponse = toFaqResponse(faqDao.findAll());
        Integer quantity = faqResponse.getContent().size();
        LogPrinter.warn("[FaqService | Find All] {} Faq Story(ies) were found", quantity);
        return faqResponse;
    }

    private FaqResponse toFaqResponse(List<FaqStory> faqStories) {
        return FaqResponse.builder().content(faqStories.stream().map(toFaqStoryInfo()).toList()).build();
    }

    private Function<FaqStory, FaqStoryInfo> toFaqStoryInfo() {
        return s ->  FaqStoryInfo.builder().question(s.getQuestion()).answer(s.getAnswer()).build();
    }
}