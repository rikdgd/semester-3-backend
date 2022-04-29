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

    @Autowired
    private ExpansionService expansionService;

    @GetMapping("/allExpansions")
    public List<Expansion> GetAllExpansions(){
        return expansionService.GetAllExpansions();
    }
}
