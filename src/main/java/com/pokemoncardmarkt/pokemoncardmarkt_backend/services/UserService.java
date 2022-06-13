package com.pokemoncardmarkt.pokemoncardmarkt_backend.services;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.AppUser;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Role;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.RoleRepository;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IAppUserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<AppUser> GetAllUsers(){
        return userRepository.findAll();
    }

    public AppUser GetUserById(long id){
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public AppUser SaveUser(AppUser appUser){
        return userRepository.save(appUser);
    }

    @Override
    public Role SaveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void AddRoleToUser(String name, String roleName) {
        AppUser user = userRepository.findAppUserByName(name);
        Role role = roleRepository.findRoleByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser GetUserByName(String name) {
        return userRepository.findAppUserByName(name);
    }

    public long Login(String username, String password){
        try{
            AppUser loginAppUser = userRepository.getUserByNameAndPassword(username, password);
            return loginAppUser.getId();
        }
        // If login failed, return impossible id to communicate failure.
        catch(Exception ex){
            return -1;
        }
    }
}
