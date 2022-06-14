package com.pokemoncardmarkt.pokemoncardmarkt_backend.services;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.AppUser;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Role;

import java.util.List;

public interface IAppUserService {
    AppUser SaveUser(AppUser user);
    Role SaveRole(Role role);
    void AddRoleToUser(String username, String role);
    AppUser GetUserByName(String name);
    List<AppUser> GetAllUsers();
}
