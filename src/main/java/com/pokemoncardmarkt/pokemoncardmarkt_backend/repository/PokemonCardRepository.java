package com.pokemoncardmarkt.pokemoncardmarkt_backend.repository;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.PokemonCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonCardRepository extends JpaRepository<PokemonCard, Long> {
}