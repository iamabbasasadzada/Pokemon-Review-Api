package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("pokemon/{pokemonId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable Long pokemonId ){
        return ResponseEntity.ok(reviewService.getReviewByPokemonId(pokemonId));
    }

    @PostMapping("pokemon/{pokemonId}/review/create")
    public ResponseEntity<ReviewDto> createReview( @PathVariable(value = "pokemonId") Long pokemonId,@RequestBody ReviewDto reviewDto){
        return ResponseEntity.ok(reviewService.createReview(reviewDto,pokemonId));
    }

    @GetMapping("pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "pokemonId") Long pokemonId,
                                                   @PathVariable(value = "reviewId") Long reviewId){

        return ResponseEntity.ok(reviewService.getReviewById(pokemonId,reviewId));
    }

    @PutMapping("pokemon/{pokemonId}/review/{reviewId}/update")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "pokemonId") Long pokemonId,
                                                   @PathVariable(value = "reviewId") Long reviewId,
                                                  @RequestBody ReviewDto reviewDto){

        return ResponseEntity.ok(reviewService.updateReview(pokemonId,reviewId,reviewDto));
    }

    @DeleteMapping("pokemon/{pokemonId}/review/{reviewId}/delete")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "pokemonId") Long pokemonId,
                                                  @PathVariable(value = "reviewId") Long reviewId){

        reviewService.deleteReview(pokemonId,reviewId);
        return ResponseEntity.ok("Review deleted successfully");
    }

}
