package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.exception.PokemonNotFoundException;
import com.pokemonreview.api.exception.ReviewNotFoundException;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.model.Review;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.repository.ReviewRepository;
import com.pokemonreview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.pokemonreview.api.mapper.ReviewMapper.reviewDtoToReview;
import static com.pokemonreview.api.mapper.ReviewMapper.reviewToReviewDto;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public ReviewDto createReview(ReviewDto reviewDto, Long pokemonId) {
        Review review = reviewDtoToReview(reviewDto);
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(()-> new PokemonNotFoundException("There is no Pokemon with this id"));
        review.setPokemon(pokemon);
        Review createdReview = reviewRepository.save(review);
        return reviewToReviewDto(createdReview);
    }

    @Override
    public List<ReviewDto> getReviewByPokemonId(Long pokemonId) {
        List<Review> reviews = reviewRepository.findByPokemonId(pokemonId);

        return reviews.stream().map(review -> reviewToReviewDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(Long pokemonId, Long reviewId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("There is no Pokemon with this id"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new ReviewNotFoundException("There is no Review with this id"));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review doesn't belong this pokemon");
        }

        return reviewToReviewDto(review);
    }

    @Override
    public ReviewDto updateReview(Long pokemonId, Long reviewId, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("There is no Pokemon with this id"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new ReviewNotFoundException("There is no Review with this id"));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review doesn't belong this pokemon");
        }
        review.setContent(reviewDto.getContent());
        review.setTitle(reviewDto.getTitle());
        review.setStars(reviewDto.getStars());
        Review updatedReview = reviewRepository.save(review);
        return reviewToReviewDto(updatedReview);
    }

    @Override
    public void deleteReview(Long pokemonId, Long reviewId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("There is no Pokemon with this id"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new ReviewNotFoundException("There is no Review with this id"));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review doesn't belong this pokemon");
        }
        reviewRepository.delete(review);
    }
}
