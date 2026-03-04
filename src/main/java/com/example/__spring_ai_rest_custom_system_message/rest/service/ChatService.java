package com.example.__spring_ai_rest_custom_system_message.rest.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

import com.example.__spring_ai_rest_custom_system_message.llm.chatClient.ChatClientProvider;
import com.example.__spring_ai_rest_custom_system_message.llm.chatModel.ChatModelProvider;

@Service
public class ChatService {

    private final ChatClientProvider chatClientProvider;
    private final ChatModelProvider chatModelProvider;

    public ChatService(ChatClientProvider chatClientProvider, ChatModelProvider chatModelProvider) {
        this.chatClientProvider = chatClientProvider;
        this.chatModelProvider = chatModelProvider;
    }

    public record ChatResult(String content, String model, Long tokensUsed) {
    }

    public ChatResult getChatResponse(String userMessage) {
        ChatClient chatClient = chatClientProvider.chatClient();
        ChatResponse response = chatClient.prompt()
                .user(userMessage)
                .call()
                .chatResponse();

        String content = response.getResult().getOutput().getText();
        String model = chatModelProvider.getModelName();
        
        // Token usage - extracted from metadata if available
        Long tokensUsed = null;
        if (response.getMetadata() != null) {
            Object usage = response.getMetadata().get("usage");
            if (usage != null) {
                try {
                    // Try to invoke getTotalTokens via reflection
                    var method = usage.getClass().getMethod("getTotalTokens");
                    tokensUsed = (Long) method.invoke(usage);
                } catch (Exception e) {
                    // Unable to get token usage
                }
            }
        }

        return new ChatResult(content, model, tokensUsed);
    }
}

