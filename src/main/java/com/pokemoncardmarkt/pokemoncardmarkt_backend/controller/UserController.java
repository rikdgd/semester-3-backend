package com.pokemoncardmarkt.pokemoncardmarkt_backend.controller;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.AppUser;
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
    public List<AppUser> GetAllUsers(){
        return userService.GetAllUsers();
    }

    @GetMapping("/users/{id}")
    public AppUser GetUserById(@PathVariable long id){
        return userService.GetUserById(id);
    }

    @PostMapping("/create_account")
    public AppUser CreateUser(@RequestBody AppUser appUser){
        return userService.SaveUser(appUser);
    }

    @GetMapping("login/{username}/{password}")
    public long login(@PathVariable String username, @PathVariable String password){
        return userService.Login(username, password);
    }
}
