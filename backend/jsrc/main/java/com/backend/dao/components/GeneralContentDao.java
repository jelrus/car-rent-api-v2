package com.backend.dao.components;

import com.backend.models.table.AboutUsStory;
import com.backend.models.table.FaqStory;

import java.util.List;

/**
 * GeneralContentDao is the interface, provides contracts for interaction with DynamoDB tables related to FaqStory and
 * AboutUs entities.
 */
public interface GeneralContentDao {

    /**
     * Contract for finding all entities of type FaqStory in DynamoDB table.
     *
     * @return {@code List<FaqStory>} found FaqStory entities in DynamoDB FaqStories table
     */
    List<FaqStory> findFaqStories();

    /**
     * Contract for finding all entities of type AboutUsStory in DynamoDB table.
     *
     * @return {@code List<AboutUsStory>} found AboutUsStory entities in DynamoDB AboutUsStories table
     */
    List<AboutUsStory> findAboutUsStories();
}