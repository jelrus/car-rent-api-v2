package com.backend.dto;

public class FAQStory {
    private String question;
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public FAQStory(String answer, String question) {
        this.answer = answer;
        this.question = question;
    }

    public FAQStory() {
    }
}
