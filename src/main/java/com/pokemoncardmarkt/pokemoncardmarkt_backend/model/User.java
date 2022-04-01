package com.pokemoncardmarkt.pokemoncardmarkt_backend.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String Name;
    private String Email;
    private String Password;

    public User(){

    }

    public User(long id, String name, String email, String password) {
        Id = id;
        Name = name;
        Email = email;
        Password = password;
    }

    public long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
