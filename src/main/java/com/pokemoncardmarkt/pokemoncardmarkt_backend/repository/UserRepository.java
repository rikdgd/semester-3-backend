package com.pokemoncardmarkt.pokemoncardmarkt_backend.repository;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    boolean existsByNameAndPassword(String name, String password);
    void deleteUserById(long id);
    AppUser getUserByNameAndPassword(String name, String password);
    AppUser findAppUserByName(String name);

}
