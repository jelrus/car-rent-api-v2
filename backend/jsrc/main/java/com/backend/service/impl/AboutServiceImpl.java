package com.backend.service.impl;

import com.backend.dao.AboutDao;
import com.backend.models.dto.request.AboutUsStoryInfo;
import com.backend.models.dto.response.AboutUsResponse;
import com.backend.service.AboutService;

import java.util.ArrayList;
import java.util.List;

public class AboutServiceImpl implements AboutService {

    private final AboutDao aboutDao;

    public AboutServiceImpl(AboutDao aboutDao) {
        this.aboutDao = aboutDao;
    }

    @Override
    public AboutUsResponse findAll() {
        List<AboutUsStoryInfo> rsl = new ArrayList<>();

        aboutDao.findAll().forEach(about -> {
            AboutUsStoryInfo aboutUsStoryInfo = new AboutUsStoryInfo();
            aboutUsStoryInfo.setTitle(about.getTitle());
            aboutUsStoryInfo.setNumericValue(about.getNumericValue());
            aboutUsStoryInfo.setDescription(about.getDescription());
            rsl.add(aboutUsStoryInfo);
        });
        return new AboutUsResponse(rsl);
    }
}
