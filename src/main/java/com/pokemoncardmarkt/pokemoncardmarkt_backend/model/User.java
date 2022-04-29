package com.pokemoncardmarkt.pokemoncardmarkt_backend.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "name", nullable = false)
    private String Name;

    @Column(name = "email", nullable = false)
    private String Email;

    @Column(name = "password", nullable = false)
    private String Password;
}
