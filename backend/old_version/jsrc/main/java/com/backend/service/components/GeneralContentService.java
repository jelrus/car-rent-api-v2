package com.backend.service.components;

import com.backend.models.dto.response.general.AboutUsResponse;
import com.backend.models.dto.response.general.FaqResponse;

/**
 * GeneralContentService is the interface, provides contracts for business logic operations on general content entities.
 */
public interface GeneralContentService {

    /**
     * Contract for finding all entities of type FaqStory.
     *
     * @return {@code List<FaqStory>} found FaqStory entities
     */
    FaqResponse findFaqStories();

    /**
     * Contract for finding all entities of type AboutUsStory.
     *
     * @return {@code List<AboutUsStory>} found AboutUsStory entities
     */
    AboutUsResponse findAboutUsStories();
}