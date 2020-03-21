package com.miage.altea.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.bo.Translation;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository {

    private Map<Locale, List<Translation>> translations;
    private List<Translation> defaultTranslations;

    public TranslationRepositoryImpl() {
        try {
            var objectMapper = new ObjectMapper();
            var frenchTranslationStream = new ClassPathResource("translations-fr.json").getInputStream();
            var frenchTranslationsArray = objectMapper.readValue(frenchTranslationStream, Translation[].class);
            var englishTranslationStream = new ClassPathResource("translations-en.json").getInputStream();
            var englishTranslationsArray = objectMapper.readValue(englishTranslationStream, Translation[].class);
            this.translations = Map.of(Locale.FRENCH, List.of(frenchTranslationsArray),Locale.ENGLISH, List.of(englishTranslationsArray)
            );
            this.defaultTranslations = List.of(englishTranslationsArray);
        } catch (IOException e) {e.printStackTrace();}
    }
    private Translation getTranslationForId(List<Translation> translations, int id) {
        return translations.stream().filter(translation -> translation.getId() == id).findFirst().get();
    }
    @Override
    public String getPokemonName(int id, Locale locale) {
        if (!translations.containsKey(locale)){
            return getTranslationForId(defaultTranslations, id).getName();
        }else{
            return getTranslationForId(translations.get(locale), id).getName();
        }
    }
}