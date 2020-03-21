package com.miage.altea.service;

import com.miage.altea.bo.PokemonType;
import com.miage.altea.repository.PokemonTypeRepository;
import com.miage.altea.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    public PokemonTypeRepository pokemonTypeRepository;
    public TranslationRepository translationRepository;

    @Autowired
    public PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository, TranslationRepository translationRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
        this.translationRepository = translationRepository;
    }

    public PokemonTypeServiceImpl() {

    }

    @Override
    public PokemonType getPokemonFromId(int id) {
        PokemonType pokemonType = pokemonTypeRepository.findPokemonTypeById(id);
        if(pokemonType == null) return null;
        pokemonType.setName(translationRepository.getPokemonName(id, LocaleContextHolder.getLocale()));
        return pokemonType;
    }

    @Override
    public PokemonType getPokemonTypeFromName(String name) {
        return pokemonTypeRepository.findPokemonTypeByName(name);
    }

    @Override
    public List<PokemonType> getPokemonTypeFromType(List<String> type) {
        return pokemonTypeRepository.findPokemonsTypeByType(type);
    }

    @Override
    public List<PokemonType> getAllPokemonTypes() {
        List<PokemonType> pokemonTypes = pokemonTypeRepository.findAllPokemonType();
        return pokemonTypes.stream().peek(pokemonType->pokemonType.setName(translationRepository.getPokemonName(pokemonType.getId(),LocaleContextHolder.getLocale()))).collect(Collectors.toList());
    }

    @Autowired
    public void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    @Autowired
    public void setTranslationRepository(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    @Override
    public PokemonType getPokemonType(int id) {
        return pokemonTypeRepository.findPokemonTypeById(id);
    }
}