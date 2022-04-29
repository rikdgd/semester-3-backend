package com.pokemoncardmarkt.pokemoncardmarkt_backend.model;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.PokemonTypes;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "pokemon_cards")
public class PokemonCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "name", nullable = false)
    private String Name;

    @Column(name = "health_points", nullable = false)
    private int HealthPoints;

    @Column(name = "type")
    private PokemonTypes Type;

    @Column(name = "expansion")
    private String Expansion;
}
