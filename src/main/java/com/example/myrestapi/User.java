package com.example.myrestapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

//Authentifizieren, dass ich wirklich ich bin
//autorisieren heisst icb darf

//für den Anfang hat die Order der annotations keine Auswirkung

@Entity
public class User {

    //GenerationType Identity sagt er erhäht es mir fortlaufend in derselben Tabelle
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    //Offensichtlich muss man die Kennzeichnung @OneToMany bei dem One machen das ganz viele manies hat
    //@JoinColumn ist dort wo er entsprechend der Verbindung nach User IDs suchen kann
    @OneToMany
    @JoinColumn(name="userId")
    private Set<ToDo> toDos;

    private String secret;

    public String getSecret() {
        return secret;
    }

    //kann bei gettern, settern und feldern angewwand werden.
    //wenn ich es bei einem setter anwende wird das Feld nicht gesetzt
    //bei gettern und fields wird es nicht serialsiert
    @JsonIgnore
    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //verschlüsseln password java - sollte man salt verwenden

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ToDo> getToDos() {
        return toDos;
    }

    public void setToDos(Set<ToDo> toDos) {
        this.toDos = toDos;
    }
}
