package apptranslator.service;

import apptranslator.repository.TranslationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TranslationService {
    private final TranslationRepository translationRepository;
    private final YandexTranslationService yandexTranslationService;
    private final TextParsingService textParsingService;

    public TranslationService(TranslationRepository translationRepository,
                              YandexTranslationService yandexTranslationService,
                              TextParsingService textParsingService) {
        this.translationRepository = translationRepository;
        this.yandexTranslationService = yandexTranslationService;
        this.textParsingService = textParsingService;
    }

    public List<String> translate(String text, String targetLang) {
        List<String> wordList = textParsingService.parseText(text);
        List<String> translatedWordList = yandexTranslationService.translate(wordList, targetLang);

        IntStream.range(0, translatedWordList.size())
                .boxed()
                .collect(Collectors.toMap(wordList::get, translatedWordList::get))
                .forEach((key, value) -> translationRepository.insertTranslationRequestInfo(key,
                        value, targetLang, LocalDateTime.now(), "21345"));

        return translatedWordList;
    }
}

