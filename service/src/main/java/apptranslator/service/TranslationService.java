package apptranslator.service;

import apptranslator.entity.TranslationRequestInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface TranslationService {

    List<String> translate(String text, String targetLang, String host);

    default List<TranslationRequestInfo> translationInfoFormer(List<String> wordList,
                                                               List<String> translatedWordList,
                                                               String targetLang, String host) {
        return IntStream.range(0, translatedWordList.size())
                .boxed()
                .collect(Collectors.toMap(wordList::get, translatedWordList::get))
                .entrySet()
                .stream()
                .map(keyValue -> {
                    TranslationRequestInfo translationRequestInfo = new TranslationRequestInfo();
                    translationRequestInfo.setSourceWord(keyValue.getKey());
                    translationRequestInfo.setTranslatedWord(keyValue.getValue());
                    translationRequestInfo.setIp(host);
                    translationRequestInfo.setTargetLang(targetLang);
                    translationRequestInfo.setLocalDateTime(LocalDateTime.now());
                    return translationRequestInfo;
                }).collect(Collectors.toList());
    }
}
