package com.miage.altea.service;

import com.miage.altea.bo.PokemonType;
import com.miage.altea.repository.PokemonTypeRepository;
import com.miage.altea.repository.TranslationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonTypeServiceImplTest {

    @Test
    void applicationContext_shouldLoadPokemonTypeService(){
        var context = new AnnotationConfigApplicationContext("com.miage.altea");
        var serviceByName = context.getBean("pokemonTypeServiceImpl");
        var serviceByClass =context.getBean(PokemonTypeServiceImpl.class);
        assertEquals(serviceByName, serviceByClass);
        assertNotNull(serviceByName);
        assertNotNull(serviceByClass);
    }

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindById(){
        var pokemonTypeRepository =mock(PokemonTypeRepository.class);
        var translationRepository = mock(TranslationRepository.class);
        var pokemonTypeService = new PokemonTypeServiceImpl(pokemonTypeRepository, translationRepository);
        pokemonTypeService.getPokemonFromId(25);
        verify(pokemonTypeRepository).findPokemonTypeById(25);
    }

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindAll(){
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var pokemonTypeService = new PokemonTypeServiceImpl();
        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);
        pokemonTypeService.getAllPokemonTypes();
        verify(pokemonTypeRepository).findAllPokemonType();
    }

    @Test
    void pokemonTypeRepository_shouldBeAutowired_withSpring(){
        var context = new AnnotationConfigApplicationContext("com.miage.altea");
        var service = context.getBean(PokemonTypeServiceImpl.class);
        assertNotNull(service.pokemonTypeRepository);
    }

    @Test
    void pokemonNames_shouldBeTranslated_usingLocaleResolver(){
        var pokemonTypeService = new PokemonTypeServiceImpl();
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);
        when(pokemonTypeRepository.findPokemonTypeById(25)).thenReturn(new PokemonType());
        var translationRepository = mock(TranslationRepository.class);
        pokemonTypeService.setTranslationRepository(translationRepository);
        when(translationRepository.getPokemonName(25, Locale.FRENCH)).thenReturn("Pikachu-FRENCH");
        LocaleContextHolder.setLocale(Locale.FRENCH);
        var pikachu = pokemonTypeService.getPokemonFromId(25);
        assertEquals("Pikachu-FRENCH", pikachu.getName());
        verify(translationRepository).getPokemonName(25, Locale.FRENCH);
    }

    @Test
    void allPokemonNames_shouldBeTranslated_usingLocaleResolver(){
        var pokemonTypeService = new PokemonTypeServiceImpl();
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);
        var pikachu = new PokemonType();
        pikachu.setId(25);
        var raichu = new PokemonType();
        raichu.setId(26);
        when(pokemonTypeRepository.findAllPokemonType()).thenReturn(List.of(pikachu, raichu));
        var translationRepository = mock(TranslationRepository.class);
        pokemonTypeService.setTranslationRepository(translationRepository);
        when(translationRepository.getPokemonName(25, Locale.FRENCH)).thenReturn("Pikachu-FRENCH");
        when(translationRepository.getPokemonName(26, Locale.FRENCH)).thenReturn("Raichu-FRENCH");
        LocaleContextHolder.setLocale(Locale.FRENCH);
        var pokemonTypes = pokemonTypeService.getAllPokemonTypes();
        assertEquals("Pikachu-FRENCH", pokemonTypes.get(0).getName());
        assertEquals("Raichu-FRENCH", pokemonTypes.get(1).getName());
        assertTrue(true);
        verify(translationRepository).getPokemonName(25, Locale.FRENCH);
        verify(translationRepository).getPokemonName(26, Locale.FRENCH);
    }

}