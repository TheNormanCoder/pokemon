package com.pokemon.pokemon.com.pokemon.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(String s) {
        super(s);
    }

    public PokemonNotFoundException() {
        super();
    }
}
