package com.concept.ai_arbitroy.Service;

import org.springframework.stereotype.Service;
import com.concept.ai_arbitroy.Entity.Feedback;
import com.concept.ai_arbitroy.dto.FeedbackAnalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FeedbackService {
    
    private final FeedbackAnalysisService analysisService;
    private final List<Feedback> feedbacks = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public FeedbackService(FeedbackAnalysisService analysisService) {
        this.analysisService = analysisService;
        // Add some sample data
        addSampleData();
    }

    public Feedback saveFeedback(String name, String product, String comment) {
        // Analyze the feedback using AI
        FeedbackAnalysis analysis = analysisService.analyzeFeedback(comment, product);
        
        // Create feedback with analysis results
        Feedback feedback = Feedback.builder()
                .id(idCounter.getAndIncrement())
                .name(name)
                .product(product)
                .comment(comment)
                .category(analysis.getCategory())
                .sentiment(analysis.getSentiment())
                .title(analysis.getTitle())
                .build();
        
        feedbacks.add(feedback);
        return feedback;
    }

    public List<Feedback> getAllFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    private void addSampleData() {
        // Sample feedback for demonstration
        Feedback sample1 = Feedback.builder()
                .id(idCounter.getAndIncrement())
                .name("John Doe")
                .product("Product A")
                .comment("Great product! Really satisfied with the quality.")
                .category("compliment")
                .sentiment("positive")
                .title("Quality Satisfaction")
                .build();

        Feedback sample2 = Feedback.builder()
                .id(idCounter.getAndIncrement())
                .name("Jane Smith")
                .product("Product B")
                .comment("The delivery was delayed by 3 days. Very disappointed.")
                .category("complaint")
                .sentiment("negative")
                .title("Delivery Delay Issue")
                .build();

        feedbacks.add(sample1);
        feedbacks.add(sample2);
    }
}