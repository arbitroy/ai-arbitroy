package com.concept.ai_arbitroy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concept.ai_arbitroy.Service.SimpleChatService;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;

@Controller
public class WebChatController {

    private final SimpleChatService simpleChatService;

    public WebChatController(SimpleChatService simpleChatService) {
        this.simpleChatService = simpleChatService;
    }

    @GetMapping("/")
    public String index() {
        return "chat";
    }


    @PostMapping("/chat")
    @HxRequest
    public String chat(@RequestParam String message, Model model) {
        try {
            String response = simpleChatService.chat(message);
            model.addAttribute("userMessage", message);
            model.addAttribute("aiResponse", response);
            return "fragments/chat-response";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to get response: " + e.getMessage());
            return "fragments/error";
        }
    }
}