package com.pokemoncardmarkt.pokemoncardmarkt_backend.controller;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.PokemonCard;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.PokemonCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class CardController {

    @Autowired
    private PokemonCardRepository pokemonCardRepository;

    @GetMapping("/allCards")
    public List<PokemonCard> GetAllCards(){
        return pokemonCardRepository.findAll();
    }

    @GetMapping("/card/{id}")
    public PokemonCard GetCardById(@PathVariable long id) {
        return pokemonCardRepository.findById(id).orElseThrow();
    }
}
