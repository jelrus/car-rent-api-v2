package com.backend.mapper;

import com.backend.models.dto.request.FeedbackInfo;
import com.backend.models.table.FeedbackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330")
public interface FeedbackMapper {
    FeedbackInfo feedbackEntityToFeedbackInfo(FeedbackEntity feedback);

}
