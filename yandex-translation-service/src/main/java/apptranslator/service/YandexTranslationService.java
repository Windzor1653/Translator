package apptranslator.service;

import apptranslator.config.RestTemplateConfig;
import apptranslator.dto.TranslateRequestDto;
import apptranslator.dto.TranslateResponseDto;
import apptranslator.dto.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class YandexTranslationService {
    @Value("${yandex.translate.api-url}")
    private String apiURL;

    @Value("${yandex.translate.folder-id}")
    private String folderID;

    @Value("${yandex.translate.key-id}")
    private String keyId;

    private final RestTemplate restTemplate;

    @Autowired
    public YandexTranslationService (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> translate(List<String> words, String targetLang) {
        TranslateRequestDto request = new TranslateRequestDto(folderID, words, targetLang);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Api-Key " + keyId);
        HttpEntity<TranslateRequestDto> entity = new HttpEntity<>(request, headers);
        ResponseEntity<TranslateResponseDto> response =
                restTemplate.postForEntity(apiURL, entity, TranslateResponseDto.class);

        return response.getBody()
                .getTranslations()
                .stream()
                .map(Translation::getText)
                .toList();
    }
}
