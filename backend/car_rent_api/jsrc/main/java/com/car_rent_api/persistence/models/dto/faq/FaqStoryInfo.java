package com.car_rent_api.persistence.models.dto.faq;

import com.google.gson.annotations.Expose;

public class FaqStoryInfo {

    @Expose
    private String question;

    @Expose
    private String answer;

    public FaqStoryInfo() {}

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public static Builder builder() {
        return new FaqStoryInfo().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder question(String question) {
            FaqStoryInfo.this.question = question;
            return this;
        }

        public Builder answer(String answer) {
            FaqStoryInfo.this.answer = answer;
            return this;
        }

        public FaqStoryInfo build() {
            return FaqStoryInfo.this;
        }
    }
}