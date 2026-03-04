package com.example.__spring_ai_rest_custom_system_message.llm.system;

/**
 * Generic interface for system prompt providers.
 * Implement this interface to add support for different prompt strategies.
 * 
 * Examples of implementations:
 * - FileSystemPromptProvider (loads from file)
 * - InlinePromptProvider (inline string)
 * - DatabasePromptProvider (from database)
 */
public interface SystemPromptProvider {
    
    /**
     * Returns the system prompt to be used in chat interactions.
     * 
     * @return The system prompt string
     */
    String getSystemPrompt();
    
    /**
     * Returns the name of the prompt provider
     */
    default String getProviderName() {
        return "SystemPromptProvider";
    }
}

