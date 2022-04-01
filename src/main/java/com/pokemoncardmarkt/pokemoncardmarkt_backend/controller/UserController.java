package com.pokemoncardmarkt.pokemoncardmarkt_backend.controller;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.User;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> GetAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User GetUserById(@PathVariable long id){
        return userRepository.findById(id).orElseThrow();
    }
}
