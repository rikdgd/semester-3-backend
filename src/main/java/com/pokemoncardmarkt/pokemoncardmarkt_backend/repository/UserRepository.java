package com.pokemoncardmarkt.pokemoncardmarkt_backend.repository;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // todo: should not return boolean, but long with userid
    boolean existsByNameAndPassword(String name, String password);

}
