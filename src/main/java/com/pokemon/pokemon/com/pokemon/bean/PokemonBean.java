package com.pokemon.pokemon.com.pokemon.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity(name = "pokemon")
public class PokemonBean {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @Column
    @NonNull
    private String name;

    @Column(length = 2000)
    @NonNull
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
