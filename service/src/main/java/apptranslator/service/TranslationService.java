package apptranslator.service;

import apptranslator.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class TranslationService {
    //private final TranslationRepository translationRepository;
    private final YandexTranslationService yandexTranslationService;

    @Autowired
    public TranslationService(//TranslationRepository translationRepository,
                              YandexTranslationService yandexTranslationService) {
        //this.translationRepository = translationRepository;
        this.yandexTranslationService = yandexTranslationService;
    }

    public List<String> translate(String text) {
        List<String> wordList = parseText(text);
        List<String> translatedWordList = yandexTranslationService.translate(wordList);
        return translatedWordList;
    }

    private List<String> parseText(String text) {
        return Arrays.stream(text.toLowerCase(Locale.ROOT).split("\\s")).toList();
    }
}

