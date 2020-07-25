package com.pokemon.pokemon.com.pokemon.service;

import com.pokemon.pokemon.com.pokemon.bean.PokemonBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonService  extends JpaRepository<PokemonBean,Integer>{

    public List<PokemonBean> findAllByName(String name);
}
