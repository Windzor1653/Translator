package apptranslator.controller;

import apptranslator.dto.TextToTranslateDto;
import apptranslator.service.TranslationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/text")
@Tag(name = "TranslationController", description = "Контроллер перевода текста")
public class TranslationController {
    private final TranslationService translationService;

    @Autowired
    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping("")
    @Operation(summary = "Разделение текста на слова и перевод")
    public ResponseEntity<List<String>> translationRequest(@RequestBody TextToTranslateDto dto) {
        List<String> translationResult = translationService.translate(dto.getText(), dto.getTargetLang());
        return ResponseEntity.ok(translationResult);
    }
}
