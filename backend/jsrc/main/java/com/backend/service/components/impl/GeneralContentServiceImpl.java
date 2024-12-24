package com.backend.service.components.impl;

import com.backend.dao.components.GeneralContentDao;
import com.backend.exception.ContentNotFoundException;
import com.backend.mapper.GeneralContentMapper;
import com.backend.models.dto.response.general.AboutUsResponse;
import com.backend.models.dto.response.general.FaqResponse;
import com.backend.models.table.AboutUsStory;
import com.backend.models.table.FaqStory;
import com.backend.service.components.GeneralContentService;
import com.google.gson.Gson;

import java.util.List;

/**
 * GeneralContentServiceImpl is the implementation of GeneralContentService interface, provides business logic
 * operations on general content entities, serves as mediator between handlers and dao layers.
 */
public class GeneralContentServiceImpl implements GeneralContentService {

    /**
     * Provides GeneralContentDao object for low-level requests to DynamoDB Client.
     */
    private final GeneralContentDao generalContentDao;

    /**
     * Provides Gson for mapping objects into JSON format.
     */
    private final Gson gson;

    /**
     * Constructs GeneralContentServiceImpl object with injected GeneralContentDao and Gson.
     *
     * @param generalContentDao {@code GeneralContentDao} injected GeneralContentDao
     * @param gson {@code Gson} injected Gson
     */
    public GeneralContentServiceImpl(GeneralContentDao generalContentDao, Gson gson) {
        this.generalContentDao = generalContentDao;
        this.gson = gson;
    }

    /**
     * Finds all entities of type FaqStory and aggregates them into FaqResponse, throws on error if no content was
     * found.
     *
     * @return {@code FaqResponse} response with found and mapped FaqStory entities
     */
    @Override
    public FaqResponse findFaqStories() {
        List<FaqStory> faqStories = generalContentDao.findFaqStories();

        if (faqStories == null || faqStories.isEmpty()) {
            throw new ContentNotFoundException("FaqStories could not be found");
        }

        FaqResponse faqResponse = new FaqResponse();
        faqResponse.setContent(faqStories.stream().map(GeneralContentMapper::toFaqResponse).toList());

        return faqResponse;
    }

    /**
     * Finds all entities of type FaqStory and aggregates them into AboutUsResponse, throws on error if no content was
     * found.
     *
     * @return {@code AboutUsResponse} response with found and mapped AboutUsStory entities
     */
    @Override
    public AboutUsResponse findAboutUsStories() {
        List<AboutUsStory> aboutUsStories = generalContentDao.findAboutUsStories();

        if (aboutUsStories == null || aboutUsStories.isEmpty()) {
            throw new ContentNotFoundException("AboutUsStories could not be found");
        }

        AboutUsResponse aboutUsResponse = new AboutUsResponse();
        aboutUsResponse.setContent(aboutUsStories.stream().map(GeneralContentMapper::toAboutUsResponse).toList());

        return aboutUsResponse;
    }
}