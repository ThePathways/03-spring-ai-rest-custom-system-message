package com.example.__spring_ai_rest_custom_system_message.llm.chatClient.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.__spring_ai_rest_custom_system_message.llm.chatClient.ChatClientProvider;
import com.example.__spring_ai_rest_custom_system_message.llm.system.SystemPromptProvider;

/**
 * Default implementation of ChatClientProvider.
 * Creates a ChatClient with the system prompt from SystemPromptProvider.
 */
@Component
@Primary
public class DefaultChatClientProvider implements ChatClientProvider {

    private final ChatClient chatClient;

    public DefaultChatClientProvider(ChatModel chatModel, SystemPromptProvider systemPromptProvider) {
        // Build ChatClient with system prompt
        this.chatClient = ChatClient.builder(chatModel)
                .defaultSystem(systemPromptProvider.getSystemPrompt())
                .build();
    }

    @Override
    public ChatClient chatClient() {
        return chatClient;
    }

    @Override
    public String getProviderName() {
        return "DefaultChatClientProvider";
    }
}

