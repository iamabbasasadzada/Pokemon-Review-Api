package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.model.Role;
import com.pokemonreview.api.service.PokemonService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
@RolesAllowed("ADMIN")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getPokemons(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize" , defaultValue = "10",required = false) int pageSize
    ){
        return ResponseEntity.ok(pokemonService.getAll(pageNo,pageSize));
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> getPokemonId(@PathVariable Long id){
        PokemonDto pokemonDto = pokemonService.getPokemonById(id);
        return ResponseEntity.ok(pokemonDto);
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createPokemon(@RequestBody PokemonDto pokemonDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok(auth.getName());
    }

    @DeleteMapping("pokemon/delete/{id}")
    public ResponseEntity<String> deletePokemon(@PathVariable Long id){
        pokemonService.deletePokemon(id);
        return ResponseEntity.ok("Pokemon deleted!");
    }

    @PutMapping("pokemon/update/{id}")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto , @PathVariable("id") Long id){
        PokemonDto pokemon = pokemonService.updatePokemon(pokemonDto,id);
        return ResponseEntity.ok(pokemon);
    }

}
