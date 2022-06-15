package com.pokemoncardmarkt.pokemoncardmarkt_backend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.AppUser;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Role;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.RoleUserModel;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

    @GetMapping("/refreshtoken")
    public void RefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        final String secret = "bfsqijvkenkjykgsnvk";

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try{
                String refreshToken = authorizationHeader.substring("Bearer ".length());

                Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);

                String username = decodedJWT.getSubject();
                AppUser appUser = userService.GetUserByName(username);


                String accessToken = JWT.create()
                        .withSubject(appUser.getName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", appUser.getRoles().stream().map(Role::getName).collect(Collectors.joining()))
                        .sign(algorithm);


                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }
            catch(Exception ex) {
                response.setHeader("error", ex.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", ex.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        else {
            throw new RuntimeException("refresh token missing");
        }
    }
}
