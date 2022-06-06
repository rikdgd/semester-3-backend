package com.pokemoncardmarkt.pokemoncardmarkt_backend.controller;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Collection;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class CollectionController {

    private CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/collection/{id}")
    public Collection GetById(@PathVariable long id){
        return collectionService.GetById(id);
    }

    @GetMapping("user_collection/{userId}")
    public Collection GetByUserId(@PathVariable long userId){
        return collectionService.GetByUserId(userId);
    }

    @PostMapping("/collection/{collId}/add_card/{cardId}")
    public Collection AddCardById(@PathVariable long collId, @PathVariable long cardId){
        return collectionService.AddCardById(collId, cardId);
    }

    @PostMapping("collection/{collId}/remove_card/{cardId}")
    public Collection RemoveCardById(@PathVariable long collId, @PathVariable long cardId){
        return collectionService.RemoveCardById(collId, cardId);
    }
}
