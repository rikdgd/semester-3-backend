package com.pokemoncardmarkt.pokemoncardmarkt_backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Collections")
public class CardCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = true)
    private AppUser appUser;

    @ManyToMany
    @Column(name = "cards")
    private List<PokemonCard> cards;

    public CardCollection(AppUser appUser){
        this.appUser = appUser;
    }
}
