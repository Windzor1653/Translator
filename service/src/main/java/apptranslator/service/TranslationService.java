package apptranslator.service;

import apptranslator.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class TranslationService {
    private final TranslationRepository translationRepository;
    private final YandexTranslationService yandexTranslationService;

    //@Autowired
    public TranslationService(TranslationRepository translationRepository,
                              YandexTranslationService yandexTranslationService) {
        this.translationRepository = translationRepository;
        this.yandexTranslationService = yandexTranslationService;
    }

    public List<String> translate(String text, String targetLang) {
        List<String> wordList = parseText(text);
        List<String> translatedWordList = yandexTranslationService.translate(wordList, targetLang);

        for (int i = 0; i < translatedWordList.size(); i++) {
            translationRepository.insertTranslationRequestInfo(translatedWordList.get(i),
                    wordList.get(i), targetLang, LocalDateTime.now(), "21345");
        }

        return translatedWordList;
    }

    private List<String> parseText(String text) {
        return Arrays.stream(text.toLowerCase(Locale.ROOT).split("\\s")).toList();
    }
}

