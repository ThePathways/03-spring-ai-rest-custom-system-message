package com.example.__spring_ai_rest_custom_system_message.llm.chatModel.provider.groq;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.__spring_ai_rest_custom_system_message.llm.chatModel.ChatModelProvider;

/**
 * GroQ implementation of ChatModelProvider.
 * Uses OpenAI client with GroQ base URL to interact with GroQ LLMs.
 */
@Component
@Primary
public class GroQChatModelProvider implements ChatModelProvider {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;

    @Value("${spring.ai.openai.chat.options.model:llama-3.1-70b-versatile}")
    private String defaultModel;

    @Value("${spring.ai.openai.chat.options.temperature:0.7}")
    private Double temperature;

    @Value("${spring.ai.openai.chat.options.max-tokens:2048}")
    private Integer maxTokens;

    @Bean
    @Primary
    public ChatModel chatModel() {
        OpenAiApi openAiApi = OpenAiApi.builder()
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                .build();

        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model(defaultModel)
                .temperature(temperature)
                .maxTokens(maxTokens)
                .build();

        return OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(options)
                .build();
    }

    @Override
    public ChatModel createChatModel() {
        return chatModel();
    }

    @Override
    public String getProviderName() {
        return "GroQ";
    }

    @Override
    public String getModelName() {
        return defaultModel;
    }
}
