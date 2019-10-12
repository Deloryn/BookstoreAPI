package com.example.bookstoreapi.web.dto.review;

import com.example.bookstoreapi.entity.Review;

public class ReviewInfoDTO {
    private Long id;
    private Short rating;
    private String opinion;

    public ReviewInfoDTO(Review review) {
        id = review.getId();
        rating = review.getRating();
        opinion = review.getOpinion();
    }
}
