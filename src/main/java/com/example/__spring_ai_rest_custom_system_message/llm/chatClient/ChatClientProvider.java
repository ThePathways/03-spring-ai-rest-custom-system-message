package com.example.__spring_ai_rest_custom_system_message.llm.chatClient;

import org.springframework.ai.chat.client.ChatClient;

/**
 * Generic interface for chat client providers.
 * Implement this interface to add support for different chat client configurations.
 * 
 * Examples of implementations:
 * - DefaultChatClientProvider (with system prompt)
 * - SimpleChatClientProvider (without system prompt)
 */
public interface ChatClientProvider {
    
    /**
     * Returns a configured ChatClient instance.
     * The ChatClient is used for interacting with the LLM.
     * 
     * @return ChatClient instance configured for the specific provider
     */
    ChatClient chatClient();
    
    /**
     * Returns the name of the provider
     */
    default String getProviderName() {
        return "ChatClientProvider";
    }
}
