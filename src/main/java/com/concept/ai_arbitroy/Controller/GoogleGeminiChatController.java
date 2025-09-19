package com.concept.ai_arbitroy.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.concept.ai_arbitroy.Service.SimpleChatService;
import com.concept.ai_arbitroy.dto.ChatResponse;

@RestController
@RequestMapping("/api/google-gemini")
public class GoogleGeminiChatController {

    private final SimpleChatService simpleChatService;

    public GoogleGeminiChatController(SimpleChatService simpleChatService) {
        this.simpleChatService = simpleChatService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody String message) {
        return new ChatResponse(this.simpleChatService.chat(message));
    }
}