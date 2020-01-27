package com.miage.altea.controller;

import com.miage.altea.bo.PokemonType;
import com.miage.altea.service.PokemonTypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon-types")
class PokemonTypeController {
    private PokemonTypeServiceI pokemonTypeService;

    @Autowired
    public PokemonTypeController(PokemonTypeServiceI pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @GetMapping("/")
    public List<PokemonType> getAllPokemonTypes() {
        return pokemonTypeService.getAllPokemonTypes();
    }

    @GetMapping("/{id}")
    PokemonType getPokemonTypeFromId(@PathVariable int id){
        return pokemonTypeService.getPokemonFromId(id);
    }

    @GetMapping(value = "/", params = "name")
    PokemonType getPokemonTypeFromName(@RequestParam String name){
        return pokemonTypeService.getPokemonTypeFromName(name);
    }

    @GetMapping(value = "/", params = "types")
    List<PokemonType> getPokemonsTypeFromType(@RequestParam List<String> types){
        return pokemonTypeService.getPokemonTypeFromType(types);
    }
}