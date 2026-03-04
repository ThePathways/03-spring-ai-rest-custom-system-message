package com.example.__spring_ai_rest_custom_system_message.rest.dto;

public class ChatResponse {

    private String response;
    private String model;
    private Long tokensUsed;

    public ChatResponse() {
    }

    public ChatResponse(String response, String model, Long tokensUsed) {
        this.response = response;
        this.model = model;
        this.tokensUsed = tokensUsed;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTokensUsed() {
        return tokensUsed;
    }

    public void setTokensUsed(Long tokensUsed) {
        this.tokensUsed = tokensUsed;
    }
}

