package com.concept.ai_arbitroy.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    private Long id;
    private String name;
    private String product;
    private String comment;
    private String category; // suggestion, compliment, complaint, query
    private String sentiment; // positive, negative, neutral
    private String title; // AI generated title
    private LocalDateTime createdAt;
    
    // Constructor for creating new feedback
    public Feedback(String name, String product, String comment) {
        this.name = name;
        this.product = product;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
    }
}