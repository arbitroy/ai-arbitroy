package com.concept.ai_arbitroy.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.concept.ai_arbitroy.Service.SimpleChatService;

public class CustomerFeedBackController {

    private final SimpleChatService simpleChatService;


    public CustomerFeedBackController() {
        this.simpleChatService = new SimpleChatService(null);
    }

}
