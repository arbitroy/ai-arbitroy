package com.concept.ai_arbitroy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concept.ai_arbitroy.Service.FeedbackService;
import com.concept.ai_arbitroy.Entity.Feedback;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;

@Controller
public class CustomerFeedBackController {

    private final FeedbackService feedbackService;

    public CustomerFeedBackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/feedback")
    public String feedback(Model model) {
        model.addAttribute("feedbacks", feedbackService.getAllFeedbacks());
        return "feedback";
    }

    @PostMapping("/submit-feedback")
    @HxRequest
    public String submitFeedback(
            @RequestParam String name,
            @RequestParam String product,
            @RequestParam String comment,
            Model model) {
        try {
            // Save and analyze feedback
            Feedback feedback = feedbackService.saveFeedback(name, product, comment);
            
            // Return updated table with all feedbacks
            model.addAttribute("feedbacks", feedbackService.getAllFeedbacks());
            model.addAttribute("success", "Feedback analyzed and saved successfully!");
            
            return "fragments/feedback-table";
            
        } catch (Exception e) {
            model.addAttribute("error", "Failed to analyze feedback: " + e.getMessage());
            return "fragments/feedback-error";
        }
    }
}