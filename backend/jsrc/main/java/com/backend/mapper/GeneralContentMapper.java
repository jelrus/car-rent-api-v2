package com.backend.mapper;

import com.backend.models.dto.response.general.AboutUsResponse;
import com.backend.models.dto.response.general.FaqResponse;
import com.backend.models.table.AboutUsStory;
import com.backend.models.table.FaqStory;

public class GeneralContentMapper {

    public static FaqResponse.FaqStory toFaqResponse(FaqStory faqStory) {
        FaqResponse.FaqStory faqStoryResponse = new FaqResponse.FaqStory();
        faqStoryResponse.setQuestion(faqStory.getQuestion());
        faqStoryResponse.setAnswer(faqStory.getAnswer());
        return faqStoryResponse;
    }

    public static AboutUsResponse.AboutUsStoryInfo toAboutUsResponse(AboutUsStory aboutUsStory) {
        AboutUsResponse.AboutUsStoryInfo aboutUsStoryInfo =
                new AboutUsResponse.AboutUsStoryInfo();
        aboutUsStoryInfo.setTitle(aboutUsStory.getTitle());
        aboutUsStoryInfo.setNumericValue(aboutUsStory.getNumericValue());
        aboutUsStoryInfo.setDescription(aboutUsStory.getDescription());
        return aboutUsStoryInfo;
    }
}