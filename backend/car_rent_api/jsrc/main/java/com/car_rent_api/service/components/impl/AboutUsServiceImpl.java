package com.car_rent_api.service.components.impl;

import com.car_rent_api.persistence.dao.components.AboutUsDao;
import com.car_rent_api.persistence.models.dto.about_us.AboutUsResponse;
import com.car_rent_api.persistence.models.dto.about_us.AboutUsStoryInfo;
import com.car_rent_api.persistence.models.entity.AboutUsStory;
import com.car_rent_api.service.components.AboutUsService;
import com.car_rent_api.utils.components.LogPrinter;

import java.util.List;
import java.util.function.Function;

public class AboutUsServiceImpl implements AboutUsService {

    private final AboutUsDao aboutUsDao;

    public AboutUsServiceImpl(AboutUsDao aboutUsDao) {
        this.aboutUsDao = aboutUsDao;
    }

    @Override
    public AboutUsResponse findAll() {
        LogPrinter.warn("[AboutUsService | Find All] Entering 'findAll @ AboutUsService' method");
        AboutUsResponse aboutUsResponse = toAboutUsResponse(aboutUsDao.findAll());
        Integer quantity = aboutUsResponse.getContent().size();
        LogPrinter.warn("[AboutUsService | Find All] {} About Us Story(ies) were found", quantity);
        return aboutUsResponse;
    }

    private AboutUsResponse toAboutUsResponse(List<AboutUsStory> aboutUsStories) {
        return AboutUsResponse.builder().content(aboutUsStories.stream().map(toAboutUsStoryInfo()).toList()).build();
    }

    private Function<AboutUsStory, AboutUsStoryInfo> toAboutUsStoryInfo() {
        return s ->  AboutUsStoryInfo.builder()
                .title(s.getTitle())
                .numericValue(s.getNumericValue())
                .description(s.getDescription())
                .build();
    }
}