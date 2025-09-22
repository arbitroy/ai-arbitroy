package com.concept.ai_arbitroy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackAnalysis {
    private String category; // suggestion, compliment, complaint, query
    private String sentiment; // positive, negative, neutral
    private String title; // Short descriptive title
    private String reasoning; // Brief explanation of the analysis
}