package com.backend.models.dto.response.general;

import java.util.List;

public class FaqResponse {

    private List<FaqStory> content;

    public FaqResponse() {}

    public List<FaqStory> getContent() {
        return content;
    }

    public void setContent(List<FaqStory> content) {
        this.content = content;
    }

    public static class FaqStory {
        private String question;
        private String answer;

        public FaqStory() {}

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}