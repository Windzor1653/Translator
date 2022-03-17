package apptranslator.controller;

import apptranslator.dto.TextToTranslateDto;
import apptranslator.service.TranslationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/text")
@Tag(name = "TranslationController", description = "Контроллер перевода текста")
public class TranslationController {
    private final TranslationService translationService;
    
    public TranslationController(@Qualifier("translationServiceConcurrent") TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping("")
    @Operation(summary = "Разделение текста на слова и перевод")
    public ResponseEntity<List<String>> translationRequest(@RequestHeader("host") String host,
                                                           @RequestBody TextToTranslateDto dto) {
        List<String> translationResult = translationService.translate(dto.getText(), dto.getTargetLang(), host);
        return ResponseEntity.ok(translationResult);
    }
}
