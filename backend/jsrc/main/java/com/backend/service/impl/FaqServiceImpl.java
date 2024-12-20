package com.backend.service.impl;

import com.backend.dao.FaqDao;
import com.backend.models.dto.request.FaqStory;
import com.backend.models.dto.response.FaqResponse;
import com.backend.service.FaqService;

import java.util.ArrayList;
import java.util.List;

public class FaqServiceImpl implements FaqService {

    private final FaqDao faqDao;

    public FaqServiceImpl(FaqDao faqDao) {
        this.faqDao = faqDao;
    }

    @Override
    public FaqResponse findAll() {
        List<FaqStory> rsl = new ArrayList<>();

        faqDao.findAll().forEach(faq -> {
            FaqStory faqStory = new FaqStory();
            faqStory.setAnswer(faq.getAnswer());
            faqStory.setQuestion(faq.getQuestion());
            rsl.add(faqStory);
        });
        return new FaqResponse(rsl);
    }
}
