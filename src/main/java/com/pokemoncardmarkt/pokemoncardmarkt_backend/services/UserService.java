package com.pokemoncardmarkt.pokemoncardmarkt_backend.services;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.AppUser;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Role;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.RoleRepository;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserService implements IAppUserService, UserDetailsService {

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

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
        log.info("saving user {}", appUser.getName());
        return userRepository.save(appUser);
    }

    @Override
    public Role SaveRole(Role role) {
        log.info("saving role {}", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void AddRoleToUser(String username, String roleName) {
        log.info("adding role: {} to user: {}", roleName, username);
        AppUser user = userRepository.findAppUserByName(username);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user =  userRepository.findAppUserByName(username);

        if (user == null){
            log.error("user not found...");
            throw new UsernameNotFoundException("user not found");
        }
        else {
            log.info("user {} found", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(user.getName(), user.getPassword(), authorities);
    }
}
