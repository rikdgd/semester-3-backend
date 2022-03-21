package com.pokemoncardmarkt.pokemoncardmarkt_backend.model;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.PokemonTypes;

import javax.persistence.*;


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

    public PokemonCard(){

    }

    public PokemonCard(String name, int healthPoints, PokemonTypes type, String expansion) {
        Name = name;
        HealthPoints = healthPoints;
        Type = type;
        Expansion = expansion;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getHealthPoints() {
        return HealthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        HealthPoints = healthPoints;
    }

    public PokemonTypes getType() {
        return Type;
    }

    public void setType(PokemonTypes type) {
        Type = type;
    }

    public String getExpansion() {
        return Expansion;
    }

    public void setExpansion(String expansion) {
        Expansion = expansion;
    }
}
