package com.miage.altea.service;


import com.miage.altea.bo.PokemonType;
import com.miage.altea.repository.PokemonTypeRepositoryImpl;
import com.miage.altea.repository.TranslationRepository;

import java.util.List;

public interface PokemonTypeService {
    PokemonType getPokemonFromId(int id);
    PokemonType getPokemonTypeFromName(String name);
    List<PokemonType> getPokemonTypeFromType(List<String> type);
    List<PokemonType> getAllPokemonTypes();

    void setPokemonTypeRepositoryImpl(PokemonTypeRepositoryImpl pokemonTypeRepositoryImpl);

    void setTranslationRepository(TranslationRepository translationRepository);

    PokemonType getPokemonType(int i);
}