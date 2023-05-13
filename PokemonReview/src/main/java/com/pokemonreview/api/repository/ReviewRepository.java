package com.pokemonreview.api.repository;

import com.pokemonreview.api.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByPokemonId(Long pokemonId);
}
