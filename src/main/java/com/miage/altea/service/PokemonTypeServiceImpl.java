package com.miage.altea.service;

import com.miage.altea.bo.PokemonType;
import com.miage.altea.repository.PokemonTypeRepositoryImpl;
import com.miage.altea.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    public PokemonTypeRepositoryImpl pokemonTypeRepositoryImpl;
    public TranslationRepository translationRepository;

    @Autowired
    public PokemonTypeServiceImpl(PokemonTypeRepositoryImpl pokemonTypeRepositoryImpl, TranslationRepository translationRepository) {
        this.pokemonTypeRepositoryImpl = pokemonTypeRepositoryImpl;
        this.translationRepository = translationRepository;
    }

    public PokemonTypeServiceImpl() {

    }

    @Override
    public PokemonType getPokemonFromId(int id) {
        PokemonType pokemonType = pokemonTypeRepositoryImpl.findPokemonTypeById(id);
        if(pokemonType == null) return null;
        pokemonType.setName(translationRepository.getPokemonName(id, LocaleContextHolder.getLocale()));
        return pokemonType;
    }

    @Override
    public PokemonType getPokemonTypeFromName(String name) {
        return pokemonTypeRepositoryImpl.findPokemonTypeByName(name);
    }

    @Override
    public List<PokemonType> getPokemonTypeFromType(List<String> type) {
        return pokemonTypeRepositoryImpl.findPokemonsTypeByType(type);
    }

    @Override
    public List<PokemonType> getAllPokemonTypes() {
        List<PokemonType> pokemonTypes = pokemonTypeRepositoryImpl.findAllPokemonType();
        return pokemonTypes.stream().peek(pokemonType->pokemonType.setName(translationRepository.getPokemonName(pokemonType.getId(),LocaleContextHolder.getLocale()))).collect(Collectors.toList());
    }

    @Autowired
    public void setPokemonTypeRepositoryImpl(PokemonTypeRepositoryImpl pokemonTypeRepositoryImpl) {
        this.pokemonTypeRepositoryImpl = pokemonTypeRepositoryImpl;
    }

    @Autowired
    public void setTranslationRepository(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    @Override
    public PokemonType getPokemonType(int id) {
        return pokemonTypeRepositoryImpl.findPokemonTypeById(id);
    }
}