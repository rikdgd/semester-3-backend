package com.pokemoncardmarkt.pokemoncardmarkt_backend.services;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.PokemonCard;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.PokemonCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CardService {

    @Autowired
    private PokemonCardRepository pokemonCardRepository;

    public List<PokemonCard> GetAllCards(){
        return pokemonCardRepository.findAll();
    }

    public PokemonCard GetCardById(long id){
        return pokemonCardRepository.findById(id).orElseThrow();
    }
}
