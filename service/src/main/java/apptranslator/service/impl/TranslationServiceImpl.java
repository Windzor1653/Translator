package apptranslator.service.impl;

import apptranslator.entity.TranslationRequestInfo;
import apptranslator.repository.TranslationRepository;
import apptranslator.service.TextParsingService;
import apptranslator.service.TranslationService;
import apptranslator.service.YandexTranslationRestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("translationServiceImpl")
public class TranslationServiceImpl implements TranslationService {
    private final TranslationRepository translationRepository;
    private final YandexTranslationRestService yandexTranslationRestService;
    private final TextParsingService textParsingService;

    public TranslationServiceImpl(TranslationRepository translationRepository,
                                  YandexTranslationRestService yandexTranslationRestService,
                                  TextParsingService textParsingService) {
        this.translationRepository = translationRepository;
        this.yandexTranslationRestService = yandexTranslationRestService;
        this.textParsingService = textParsingService;
    }

    public List<String> translate(String text, String targetLang, String host) {
        List<String> wordList = textParsingService.parseText(text);
        List<String> translatedWordList = yandexTranslationRestService.translate(wordList, targetLang);

        List<TranslationRequestInfo> infoList = translationInfoFormer(wordList, translatedWordList, targetLang, host);
        translationRepository.saveAll(infoList);

        return translatedWordList;
    }
}

