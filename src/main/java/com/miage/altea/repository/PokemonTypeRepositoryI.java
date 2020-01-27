package com.miage.altea.repository;

import com.miage.altea.bo.PokemonType;

import java.util.List;

public interface PokemonTypeRepositoryI {
    PokemonType findPokemonTypeById(int id);
    PokemonType findPokemonTypeByName(String name);
    List<PokemonType> findPokemonsTypeByType(List<String> type);
    List<PokemonType> findAllPokemonType();
}
