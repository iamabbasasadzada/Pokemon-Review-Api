package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;

import java.util.List;

public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAll(int pageNo , int pageSize);
    PokemonDto getPokemonById(Long id);
    void deletePokemon(Long id);
    PokemonDto updatePokemon(PokemonDto pokemonDto , Long pokemonId);
}
