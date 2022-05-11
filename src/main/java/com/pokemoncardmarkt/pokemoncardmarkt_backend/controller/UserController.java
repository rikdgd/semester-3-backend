package com.pokemoncardmarkt.pokemoncardmarkt_backend.controller;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.User;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> GetAllUsers(){
        return userService.GetAllUsers();
    }

    @GetMapping("/users/{id}")
    public User GetUserById(@PathVariable long id){
        return userService.GetUserById(id);
    }

    @PostMapping("/create_account")
    public User CreateUser(@RequestBody User user){
        return userService.CreateUser(user);
    }


}
