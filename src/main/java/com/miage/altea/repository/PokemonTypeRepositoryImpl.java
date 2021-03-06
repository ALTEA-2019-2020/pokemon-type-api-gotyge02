package com.miage.altea.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.bo.PokemonType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();
            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            var liste =Arrays.asList(pokemonsArray);
            liste.sort((pokemonType, t1) -> pokemonType.getId()>t1.getId()?1:-1);
            this.pokemons =liste;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PokemonType findPokemonTypeById(int id) {
        System.out.println("Loading Pokemon information for id " + id);
        return pokemons
                .stream()
                .filter(pokemonType -> pokemonType.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no pokemon found for id " + id));
    }

    @Override
    public PokemonType findPokemonTypeByName(String name) {
        System.out.println("Loading Pokemon for " + name);
        return pokemons
                .stream()
                .filter(pokemonType -> pokemonType.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no pokemon found for " + name));
    }

    @Override
    public List<PokemonType> findPokemonsTypeByType(List<String> type) {
        return pokemons
                .stream()
                .filter(pokemonType -> pokemonType.getTypes().containsAll(type))
                .collect(Collectors.toList());
    }

    @Override
    public List<PokemonType> findAllPokemonType() {
        return pokemons;
    }
}