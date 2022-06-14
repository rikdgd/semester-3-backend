package com.pokemoncardmarkt.pokemoncardmarkt_backend.controller;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.AppUser;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Role;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.RoleUserModel;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<List<AppUser>> GetAllUsers(){
        return ResponseEntity.ok().body(userService.GetAllUsers());
    }

    @GetMapping("/users/{id}")
    public AppUser GetUserById(@PathVariable long id){
        return userService.GetUserById(id);
    }

    @PostMapping("/create_account")
    public ResponseEntity<AppUser> CreateUser(@RequestBody AppUser appUser){
        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentContextPath().
                path("/api/v1/create_account").
                toUriString());
        return ResponseEntity.created(uri).body(userService.SaveUser(appUser));
    }

    @GetMapping("login/{username}/{password}")
    public long Login(@PathVariable String username, @PathVariable String password){
        return userService.Login(username, password);
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> SaveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentContextPath().
                path("/api/v1/role/save").
                toUriString());
        return ResponseEntity.created(uri).body(userService.SaveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> AddRoleToUser(@RequestBody RoleUserModel roleUser) {
        userService.AddRoleToUser(roleUser.getUserName(), roleUser.getRoleName());
        return ResponseEntity.ok().build();
    }
}
