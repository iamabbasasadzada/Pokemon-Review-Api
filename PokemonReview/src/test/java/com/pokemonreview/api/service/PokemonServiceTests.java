package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.impl.PokemonServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class PokemonServiceTests {
    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Test
    public void PokemonService_CreatePokemon_ReturnPokemonDto(){
        Pokemon pokemon = Pokemon.builder()
                .name("Pikachu213")
                .type("Electric")
                .build();
        PokemonDto pokemonDto = PokemonDto.builder()
                .name(pokemon.getName())
                .type(pokemon.getType())
                .build();

        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);
        PokemonDto savedPokemon = pokemonService.createPokemon(pokemonDto);

        Assertions.assertThat(savedPokemon).isNotNull();
    }

    @Test
    public void PokemonService_FindAll_ReturnPokemonResponse(){

        Page<Pokemon> pokemons = Mockito.mock(Page.class);

        when(pokemonRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pokemons);

        PokemonResponse pokemonResponse=pokemonService.getAll(1,10);
        Assertions.assertThat(pokemonResponse).isNotNull();
    }
    @Test
    public void PokemonService_FindById_ReturnPokemonDto() {
        long pokemonId = 1;
        Pokemon pokemon = Pokemon.builder().id(pokemonId).name("pikachu").type("electric").type("this is a type").build();
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.ofNullable(pokemon));

        PokemonDto pokemonReturn = pokemonService.getPokemonById(pokemonId);

        Assertions.assertThat(pokemonReturn).isNotNull();
    }

    @Test
    public void PokemonService_UpdatePokemon_ReturnPokemonDto() {
        long pokemonId = 1;
        Pokemon pokemon = Pokemon.builder().id(pokemonId).name("pikachu").type("electric").type("this is a type").build();
        PokemonDto pokemonDto = PokemonDto.builder().id(pokemonId).name("pikachu").type("electric").type("this is a type").build();

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.ofNullable(pokemon));
        when(pokemonRepository.save(pokemon)).thenReturn(pokemon);

        PokemonDto updateReturn = pokemonService.updatePokemon(pokemonDto, pokemonId);

        Assertions.assertThat(updateReturn).isNotNull();
    }

    @Test
    public void PokemonService_DeletePokemonById_ReturnVoid() {
        long pokemonId = 1;
        Pokemon pokemon = Pokemon.builder().id(pokemonId).name("pikachu").type("electric").type("this is a type").build();

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.ofNullable(pokemon));
        doNothing().when(pokemonRepository).delete(pokemon);

        assertAll(() -> pokemonService.deletePokemon(pokemonId));
    }

}

