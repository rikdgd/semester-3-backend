package com.pokemoncardmarkt.pokemoncardmarkt_backend.services;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Expansion;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.PokemonCard;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.ExpansionRepository;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.PokemonCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpansionService {

    @Autowired
    private ExpansionRepository expansionRepository;

    public List<Expansion> GetAllExpansions(){
        return expansionRepository.findAll();
    }

    public Expansion GetExpansionById(long id){
        return expansionRepository.findById(id).orElseThrow();
    }

    public Expansion CreateExpansion(Expansion expansion){
        return expansionRepository.save(expansion);
    }

//    public Expansion AddCard(long expansionId, long cardId){
//        Expansion expansion = GetExpansionById(expansionId);
//        List<PokemonCard> expansionCards = expansion.getCards();
//
//        CardService cardService = new CardService();
//        PokemonCard card = cardService.GetCardById(cardId);
//
//        expansionCards.add(card);
//        expansion.setCards(expansionCards);
//
//        return expansionRepository.save(expansion);
//    }
}
