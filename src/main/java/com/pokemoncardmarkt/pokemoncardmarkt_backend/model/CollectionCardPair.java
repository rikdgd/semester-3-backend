package com.pokemoncardmarkt.pokemoncardmarkt_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CollectionCardPair {
    private CardCollection collection;
    private PokemonCard card;
}
