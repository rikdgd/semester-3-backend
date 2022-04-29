package com.pokemoncardmarkt.pokemoncardmarkt_backend.services;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.User;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> GetAllUsers(){
        return userRepository.findAll();
    }

    public User GetUserById(long id){
        return userRepository.findById(id).orElseThrow();
    }

    public User CreateUser(User user){
        return userRepository.save(user);
    }
}
