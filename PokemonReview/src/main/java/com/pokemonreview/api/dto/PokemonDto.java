package com.pokemonreview.api.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PokemonDto {
    private Long id;
    private String name;
    private String type;
}
