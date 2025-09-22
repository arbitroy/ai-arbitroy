package com.concept.ai_arbitroy.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import com.concept.ai_arbitroy.dto.FeedbackAnalysis;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FeedbackAnalysisService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    public FeedbackAnalysisService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
        this.objectMapper = new ObjectMapper();
    }

    public FeedbackAnalysis analyzeFeedback(String feedback, String product) {
        String prompt = String.format("""
            Analyze the following customer feedback for product "%s":
            
            Feedback: "%s"
            
            Please provide a JSON response with the following structure:
            {
                "category": "one of: suggestion, compliment, complaint, query",
                "sentiment": "one of: positive, negative, neutral", 
                "title": "a short 3-6 word descriptive title",
                "reasoning": "brief explanation in 1-2 sentences"
            }
            
            Categories:
            - suggestion: customer proposing improvements or new features
            - compliment: positive feedback, praise, satisfaction
            - complaint: negative feedback, problems, dissatisfaction
            - query: questions, requests for information or clarification
            
            Sentiment:
            - positive: overall positive tone, satisfaction, praise
            - negative: dissatisfaction, problems, frustration
            - neutral: factual, neither clearly positive nor negative
            
            Return only valid JSON, no additional text.
            """, product, feedback);

        try {
            String response = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            // Clean the response in case AI adds markdown formatting
            response = response.trim();
            if (response.startsWith("```json")) {
                response = response.substring(7);
            }
            if (response.endsWith("```")) {
                response = response.substring(0, response.length() - 3);
            }
            response = response.trim();

            return objectMapper.readValue(response, FeedbackAnalysis.class);
            
        } catch (Exception e) {
            // Fallback analysis if AI fails
            return FeedbackAnalysis.builder()
                    .category("query")
                    .sentiment("neutral")
                    .title("Customer Feedback")
                    .reasoning("Analysis unavailable - manual review needed")
                    .build();
        }
    }
}