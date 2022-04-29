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

    @Column(name = "Name", nullable = false)
    private String Name;

    @OneToMany
    @Column(name = "Cards", nullable = false)
    private List<PokemonCard> Cards;

    @Column(name = "ReleaseDate", nullable = false)
    private String ReleaseDate;
}
