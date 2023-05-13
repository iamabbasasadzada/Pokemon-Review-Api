package com.pokemonreview.api.service;


import com.pokemonreview.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewDto reviewDto,Long pokemonId);
    List<ReviewDto> getReviewByPokemonId(Long pokemonId);
    ReviewDto getReviewById(Long pokemonId , Long reviewId);
    ReviewDto updateReview (Long pokemonId , Long reviewId,ReviewDto reviewDto);
    void deleteReview(Long pokemonId , Long reviewId);
}
