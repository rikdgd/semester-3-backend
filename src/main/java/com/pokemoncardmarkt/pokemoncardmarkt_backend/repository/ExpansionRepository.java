package com.pokemoncardmarkt.pokemoncardmarkt_backend.repository;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.model.Expansion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpansionRepository extends JpaRepository<Expansion, Long> {
}
