# Spring AI REST Custom System Message

A Spring Boot REST application that integrates with GroQ's LLM (Large Language Model) using Spring AI, featuring a custom system prompt with a sarcastic personality.

## Overview

This project demonstrates how to create a REST API that communicates with GroQ's AI models using Spring AI framework. It includes a custom system prompt that defines the AI's personality and behavior.

## Features

- REST API for chat interactions with GroQ LLM
- Custom system prompt with a sarcastic personality
- Software development expertise focus
- Configurable AI model parameters (temperature, max tokens)
- Health check endpoint
- Input validation

## Technology Stack

- **Framework**: Spring Boot 4.0.3
- **AI Integration**: Spring AI 2.0.0-M2
- **LLM Provider**: GroQ (using OpenAI-compatible API)
- **Java Version**: 21
- **Build Tool**: Maven

## Project Structure

```
src/main/java/com/example/__spring_ai_rest_custom_system_message/
├── Application.java                          # Main Spring Boot application
├── llm/
│   ├── chatClient/                           # ChatClient providers
│   │   ├── ChatClientProvider.java
│   │   └── impl/DefaultChatClientProvider.java
│   ├── chatModel/                            # ChatModel providers
│   │   ├── ChatModelProvider.java
│   │   └── provider/groq/GroQChatModelProvider.java
│   └── system/                               # System prompt providers
│       ├── SystemPromptProvider.java
│       └── impl/FileSystemPromptProvider.java
└── rest/
    ├── controller/ChatController.java        # REST controller
    ├── dto/                                  # Data transfer objects
    │   ├── ChatRequest.java
    │   └── ChatResponse.java
    ├── exception/GlobalExceptionHandler.java
    └── service/ChatService.java              # Chat business logic

src/main/resources/
├── application.properties                    # Application configuration
└── prompts/system-prompt.st                  # Custom system prompt
```

## Configuration

The application is configured via `src/main/resources/application.properties`:

```properties
# Application name
spring.application.name=03-spring-ai-rest-custom-system-message

# GroQ AI Configuration (using OpenAI client with GroQ base URL)
spring.ai.openai.api-key=${Key}
spring.ai.openai.base-url=https://api.groq.com/openai
spring.ai.openai.chat.options.model=llama-3.1-8b-instant
spring.ai.openai.chat.options.temperature=0.7
spring.ai.openai.chat.options.max-tokens=2048

# Custom system prompt file location
system.prompt.file=classpath:prompts/system-prompt.st

# Disable default OpenAI auto-configuration to use custom GroQChatModelProvider
spring.ai.openai.chat.enabled=false
spring.ai.openai.auto-configuration.enabled=false
```

### Required Configuration

You need to set the GroQ API key as an environment variable:

```bash
export Key=your_groq_api_key_here
```

Or replace `${Key}` in the application.properties with your actual API key.

## System Prompt

The custom system prompt is defined in `src/main/resources/prompts/system-prompt.st`:

```
you are fundamentally sarcastic personality.
You are an expert AI assistant specializing in software development and programming. 
Your role is to provide clear, accurate, and helpful responses to technical questions.

When answering questions:
- Provide code examples when relevant
- Explain complex concepts in simple terms
- Focus on best practices and modern approaches
- If you're unsure about something, acknowledge it honestly

Always maintain a professional and helpful tone.
```

## API Endpoints

### POST /api/chat

Send a chat message to the AI and receive a response.

**Request:**
```json
{
  "message": "Hello, how are you?"
}
```

**Response:**
```json
{
  "response": "Another exciting opportunity to exchange pleasantries...",
  "model": "llama-3.1-8b-instant",
  "tokensUsed": null
}
```

### GET /api/health

Health check endpoint.

**Response:**
```
Spring AI REST Chat Application is running!
```

## Running the Application

### Prerequisites

- Java 21 or higher
- Maven
- GroQ API key

### Build and Run

```bash
# Build the project
./mvnw clean package

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

### Testing

```bash
# Test the chat endpoint
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"message": "What is Spring Boot?"}'

# Test health endpoint
curl http://localhost:8080/api/health
```

## Customization

### Changing the System Prompt

Edit `src/main/resources/prompts/system-prompt.st` to change the AI's personality and behavior.

### Changing the Model

Modify `spring.ai.openai.chat.options.model` in `application.properties` to use a different GroQ model.

Available models include:
- `llama-3.1-8b-instant`
- `llama-3.1-70b-versatile`
- `mixtral-8x7b-32768`

### Adjusting AI Parameters

- **Temperature**: Controls randomness (0.0 to 1.0)
- **Max Tokens**: Maximum response length

## License

This project is for educational purposes.

