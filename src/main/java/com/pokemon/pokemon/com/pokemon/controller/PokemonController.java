package com.pokemon.pokemon.com.pokemon.controller;

import com.pokemon.pokemon.com.pokemon.bean.PokemonBean;
import com.pokemon.pokemon.com.pokemon.exeption.PokemonNotFoundException;
import com.pokemon.pokemon.com.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PokemonController {

    @Autowired
    private PokemonService service;

    @GetMapping(path = "/pokemon/{name}")
    public ResponseEntity<Object> getPokemon(@PathVariable String name) throws PokemonNotFoundException {

        List<PokemonBean> pokemonBeans=  service.findAllByName(name);
        if(pokemonBeans.size()==0){
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok().body(pokemonBeans.get(0));
    }

    @GetMapping(path = "/pokemon")
    public ResponseEntity<Object> getAllPokemon() throws PokemonNotFoundException {
        List<PokemonBean> pokemonBeans=  service.findAll();
        if(pokemonBeans.size()==0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pokemonBeans);
    }

    @PostMapping (path = "/pokemons")
    public ResponseEntity<Object> createPokemon(@RequestBody PokemonBean bean) throws PokemonNotFoundException {
        PokemonBean pokemonBeans=  service.save(bean);
        service.flush();
        if(pokemonBeans == null){
            return ResponseEntity.badRequest().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/pokemons/{id}")
                .buildAndExpand(pokemonBeans.getId()).toUri();

        return ResponseEntity.created(location).body(pokemonBeans);
    }

    @DeleteMapping(path = "/pokemon/{name}")
    public ResponseEntity<Object> deletePokemon(@PathVariable String name) throws PokemonNotFoundException {
        List<PokemonBean> pokemonBeans=  service.findAllByName(name);
        service.deleteAll(pokemonBeans);
        return ResponseEntity.ok().body(pokemonBeans.get(0));
    }

    @PutMapping (path = "/pokemons")
    public ResponseEntity<Object> updatePokemon(@RequestBody PokemonBean bean) throws PokemonNotFoundException {

        List<PokemonBean> pokemonBeans=  service.findAllByName(bean.getName());
        if(pokemonBeans == null){
            return ResponseEntity.badRequest().build();
        }
        pokemonBeans.get(0).setDescription(bean.getDescription());
        PokemonBean updated=  service.save(pokemonBeans.get(0));
        return ResponseEntity.ok().body(updated);
    }
}
