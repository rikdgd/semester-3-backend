package com.pokemoncardmarkt.pokemoncardmarkt_backend.controller;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.CardCollection;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.CollectionCardPair;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.services.CardService;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class CollectionController {

    private final CollectionService collectionService;
    private final CardService cardService;

    @Autowired
    public CollectionController(CollectionService collectionService, CardService cardService) {
        this.collectionService = collectionService;
        this.cardService = cardService;
    }

    @GetMapping("/collection/{id}")
    public CardCollection GetById(@PathVariable long id){
        return collectionService.GetById(id);
    }

    @GetMapping("user_collection/{userId}")
    public CardCollection GetByUserId(@PathVariable long userId){
        return collectionService.GetByUserId(userId);
    }

    @PostMapping("create_collection/{userId}")
    public CardCollection CreateCollectionWithUserId(@PathVariable Long userId) {
        return collectionService.CreateCollection(userId);
    }

    @PostMapping("/collection/{collectionId}/add_card/{cardId}")
    public CardCollection AddCard(@PathVariable Long collectionId, @PathVariable Long cardId){
        CollectionCardPair collectionCardPair = new CollectionCardPair(
                collectionService.GetById(collectionId),
                cardService.GetCardById(cardId)
        );

        return collectionService.AddCard(collectionCardPair);
    }

    @PostMapping("collection/remove_card")
    public CardCollection RemoveCard(@RequestBody CollectionCardPair collectionCardPair){
        return collectionService.RemoveCard(collectionCardPair);
    }
}
