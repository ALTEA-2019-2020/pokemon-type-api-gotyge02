package com.miage.altea.service;


import com.miage.altea.bo.PokemonType;

import java.util.List;

public interface PokemonTypeServiceI {
    PokemonType getPokemonFromId(int id);
    PokemonType getPokemonTypeFromName(String name);
    List<PokemonType> getPokemonTypeFromType(List<String> type);
    List<PokemonType> getAllPokemonTypes();
}

