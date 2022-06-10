package com.pokemoncardmarkt.pokemoncardmarkt_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.PokemonTypes;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@JsonIgnoreProperties(value = "expansion")

@Entity
@Table(name = "pokemonCards")
public class PokemonCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "name")
    private String Name;

    @Column(name = "health_points")
    private int HealthPoints;

    @Column(name = "type")
    private PokemonTypes Type;

    @ManyToOne
    @JoinColumn(name = "expansion_id", referencedColumnName = "Id")
    private Expansion expansion;

    @ManyToMany
    private List<Collection> collections;

    public PokemonCard(long id) {
        Id = id;
    }

    public PokemonCard(long id, Expansion expansion) {
        Id = id;
        this.expansion = expansion;
    }
}
