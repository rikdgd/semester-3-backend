package com.pokemoncardmarkt.pokemoncardmarkt_backend.repository;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.CardCollection;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<CardCollection, Long> {
    CardCollection findCollectionByUser(AppUser appUser);
}
