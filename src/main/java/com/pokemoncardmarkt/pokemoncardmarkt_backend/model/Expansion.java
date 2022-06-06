package com.pokemoncardmarkt.pokemoncardmarkt_backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Expansion")
public class Expansion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "Name")
    private String Name;

    @OneToMany(mappedBy = "expansion")
    @Column(name = "Cards")
    private List<PokemonCard> Cards;

    @Column(name = "ReleaseDate")
    private String ReleaseDate;

    public Expansion(long id) {
        Id = id;
    }
}
