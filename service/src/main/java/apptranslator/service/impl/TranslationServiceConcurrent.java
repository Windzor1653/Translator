package apptranslator.service.impl;

import apptranslator.entity.TranslationRequestInfo;
import apptranslator.repository.TranslationRepository;
import apptranslator.service.TextParsingService;
import apptranslator.service.TranslationService;
import apptranslator.service.YandexTranslationRestService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/*
Yandex translate api позволяет переводить слова, отправляя их сразу списком
в одном рест-запросе. Данный сервис добавлен для более точного соответствия
условиям задания (переводить каждое слово по отдельности).
 */
@Service("translationServiceConcurrent")
public class TranslationServiceConcurrent implements TranslationService {
    private final TranslationRepository translationRepository;
    private final YandexTranslationRestService yandexTranslationRestService;
    private final TextParsingService textParsingService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public TranslationServiceConcurrent(TranslationRepository translationRepository,
                                        YandexTranslationRestService yandexTranslationRestService,
                                        TextParsingService textParsingService) {
        this.translationRepository = translationRepository;
        this.yandexTranslationRestService = yandexTranslationRestService;
        this.textParsingService = textParsingService;
    }

    @Override
    public List<String> translate(String text, String targetLang, String host) {
        List<String> translatedWordList = Collections.emptyList();

        if (("ru".equalsIgnoreCase(targetLang)) || ("en".equalsIgnoreCase(targetLang))) {
            List<String> wordList = textParsingService.parseText(text);

            List<Callable<List<String>>> tasks = new ArrayList<>();
            wordList.forEach(word -> tasks.add(() ->
                    yandexTranslationRestService.translate(Collections.singletonList(word), targetLang)));

            List<Future<List<String>>> futures = Collections.emptyList();
            try {
                futures = executorService.invokeAll(tasks);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }

            if (!CollectionUtils.isEmpty(futures)) {
                translatedWordList = futures.stream().map(listFuture -> {
                    try {
                        return listFuture.get().get(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "";
                    }
                }).collect(Collectors.toList());

                List<TranslationRequestInfo> infoList = translationInfoFormer(wordList, translatedWordList, targetLang, host);
                translationRepository.saveAll(infoList);
            }
        }

        return translatedWordList;
    }
}
