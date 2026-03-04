package com.example.__spring_ai_rest_custom_system_message.llm.system.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.__spring_ai_rest_custom_system_message.llm.system.SystemPromptProvider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@Primary
public class FileSystemPromptProvider implements SystemPromptProvider {

    @Value("${system.prompt.file}")
    private String systemPromptLocation;

    private final ResourceLoader resourceLoader;

    public FileSystemPromptProvider(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public String getSystemPrompt() {
        try {
            Resource resource = resourceLoader.getResource(systemPromptLocation);
            return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load system prompt from: " + systemPromptLocation, e);
        }
    }

    @Override
    public String getProviderName() {
        return "FileSystemPromptProvider";
    }
}

