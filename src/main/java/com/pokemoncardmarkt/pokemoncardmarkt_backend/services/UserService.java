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

    public void DeleteUserById(long id) {
        userRepository.deleteUserById(id);
    }

    public long Login(String username, String password){
        try{
            User loginUser = userRepository.getUserByNameAndPassword(username, password);
            return loginUser.getId();
        }
        // If login failed, return impossible id to communicate failure.
        catch(Exception ex){
            return -1;
        }
    }
}
