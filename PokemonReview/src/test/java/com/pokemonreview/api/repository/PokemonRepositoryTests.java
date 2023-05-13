package com.pokemonreview.api.repository;

import com.pokemonreview.api.model.Pokemon;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@RunWith(SpringRunner.class)
public class PokemonRepositoryTests {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Test
    public void PokemonRepository_SaveAll_ReturnSavedPokemon(){
        Pokemon pokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        Assertions.assertThat(savedPokemon).isNotNull();
        Assertions.assertThat(savedPokemon.getId()).isGreaterThan(0);
    }

    @Test
    public void PokemonRepository_FindAll_ReturnMoreThanOnePokemon(){
        Pokemon pokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();
        Pokemon pokemon2 = Pokemon.builder()
                .name("Pikachu2")
                .type("Electric2")
                .build();

        pokemonRepository.save(pokemon);
        pokemonRepository.save(pokemon2);

        List<Pokemon> pokemons = pokemonRepository.findAll();
        Assertions.assertThat(pokemons).isNotNull();
        Assertions.assertThat(pokemons.size()).isEqualTo(2);
    }

    @Test
    public void PokemonRepository_FindById_ReturnPokemon(){
        Pokemon pokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        pokemonRepository.save(pokemon);

        Pokemon pokemon1 = pokemonRepository.findById(pokemon.getId()).get();
        Assertions.assertThat(pokemon1).isNotNull();
    }

    @Test
    public void PokemonRepository_FindByType_ReturnPokemon(){
        Pokemon pokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        pokemonRepository.save(pokemon);

        Pokemon pokemon1 = pokemonRepository.findByType("Electric").get();
        Assertions.assertThat(pokemon1).isNotNull();
    }

    @Test
    public void PokemonRepository_UpdatePokemon_ReturnPokemon(){
        Pokemon pokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        pokemonRepository.save(pokemon);

        Pokemon pokemon1 = pokemonRepository.findById(pokemon.getId()).get();

        pokemon1.setName("Richu");
        pokemon1.setType("Hecho");
        Pokemon updatedPokemon = pokemonRepository.save(pokemon1);
        Assertions.assertThat(updatedPokemon.getName()).isNotNull();
        Assertions.assertThat(updatedPokemon.getType()).isNotNull();
    }
    @Test
    public void PokemonRepository_DeletePokemon_ReturnPokemonIsEmpty(){
        Pokemon pokemon = Pokemon.builder()
                .name("Pikachu")
                .type("Electric")
                .build();

        pokemonRepository.save(pokemon);
        pokemonRepository.deleteById(pokemon.getId());

        Optional<Pokemon> pokemon1 = pokemonRepository.findById(pokemon.getId());
        Assertions.assertThat(pokemon1).isEmpty();
    }
}
