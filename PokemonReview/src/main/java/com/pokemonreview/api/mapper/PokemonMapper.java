package com.pokemonreview.api.mapper;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.model.Pokemon;

public class PokemonMapper {

    public static Pokemon pokemonDtoToPokemon(PokemonDto pokemonDto){
        return Pokemon.builder()
                .name(pokemonDto.getName())
                .type(pokemonDto.getType())
                .build();
    }

    public static PokemonDto pokemonToPokemonDto(Pokemon pokemon){
        return PokemonDto.builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .type(pokemon.getType())
                .build();
    }
}
