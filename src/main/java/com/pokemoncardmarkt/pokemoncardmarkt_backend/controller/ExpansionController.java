package com.pokemoncardmarkt.pokemoncardmarkt_backend.controller;


import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Expansion;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.services.ExpansionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ExpansionController {

    private ExpansionService expansionService;

    @Autowired
    public ExpansionController(ExpansionService expansionService) {
        this.expansionService = expansionService;
    }

    @GetMapping("/allExpansions")
    public List<Expansion> GetAllExpansions(){
        return expansionService.GetAllExpansions();
    }

    @GetMapping("/Expansion/{id}")
    public Expansion GetExpansionById(@PathVariable long id){
        return expansionService.GetExpansionById(id);
    }

    @PostMapping("/create_expansion")
    public Expansion CreateExpansion(@RequestBody Expansion expansion){
        return expansionService.CreateExpansion(expansion);
    }

//    @PostMapping("/AddCard")
//    public Expansion AddCard(@RequestBody long expansionId, @RequestBody long cardId){
//        return expansionService.AddCard(expansionId, cardId);
//    }
}
