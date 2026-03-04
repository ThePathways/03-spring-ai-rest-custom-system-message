package com.example.__spring_ai_rest_custom_system_message.llm.chatModel;

import org.springframework.ai.chat.model.ChatModel;

/**
 * Generic interface for chat model providers.
 * Implement this interface to add support for different LLM providers.
 * 
 * Examples of implementations:
 * - GroQChatModelProvider
 * - OpenAIChatModelProvider
 * - AnthropicChatModelProvider
 */
public interface ChatModelProvider {
    
    /**
     * Creates and returns a ChatModel instance.
     * The ChatModel is the core interface for interacting with LLM APIs.
     * 
     * @return ChatModel instance configured for the specific provider
     */
    ChatModel createChatModel();
    
    /**
     * Returns the name of the provider (e.g., "GroQ", "OpenAI", "Anthropic")
     */
    String getProviderName();

    /**
     * Returns the name of the model being used (e.g., "llama-3.1-70b-versatile")
     */
    String getModelName();
}

