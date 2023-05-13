package com.pokemonreview.api.mapper;

import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.model.Review;

public class ReviewMapper {

    public static ReviewDto reviewToReviewDto(Review review){
        return ReviewDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .stars(review.getStars())
                .title(review.getTitle())
                .build();
    }

    public static Review reviewDtoToReview(ReviewDto reviewDto){
        return Review.builder()
                .id(reviewDto.getId())
                .content(reviewDto.getContent())
                .stars(reviewDto.getStars())
                .title(reviewDto.getTitle())
                .build();
    }

}
