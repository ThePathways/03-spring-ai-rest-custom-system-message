package com.example.__spring_ai_rest_custom_system_message.rest.controller;

import com.example.__spring_ai_rest_custom_system_message.rest.dto.ChatRequest;
import com.example.__spring_ai_rest_custom_system_message.rest.dto.ChatResponse;
import com.example.__spring_ai_rest_custom_system_message.rest.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        String userMessage = request.getMessage();

        ChatService.ChatResult result = chatService.getChatResponse(userMessage);

        ChatResponse chatResponse = new ChatResponse(
                result.content(),
                result.model(),
                result.tokensUsed()
        );

        return ResponseEntity.ok(chatResponse);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Spring AI REST Chat Application is running!");
    }
}

