package com.pokemoncardmarkt.pokemoncardmarkt_backend.controller;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Expansion;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.PokemonCard;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class CardController {

    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/allCards")
    public List<PokemonCard> GetAllCards(){
        return cardService.GetAllCards();
    }

    @GetMapping("/card/{id}")
    public PokemonCard GetCardById(@PathVariable long id) {
        return cardService.GetCardById(id);
    }

    @PostMapping("/create_card")
    public PokemonCard CreateCard(@RequestBody PokemonCard card) {
        return cardService.CreateCard(card);
    }

    @PostMapping("/AddToExpansion")
    public PokemonCard AddCardToExpansion(@RequestBody PokemonCard card){
        return cardService.CreateCard(card);
    }
}
