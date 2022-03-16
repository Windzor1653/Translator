package apptranslator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TranslateRequestDto {
    @JsonProperty("folder_id")
    private String folderId;
    private List<String> texts;
    private String targetLanguageCode;

    public TranslateRequestDto(String folderId, List<String> texts, String targetLanguageCode) {
        this.folderId = folderId;
        this.texts = texts;
        this.targetLanguageCode = targetLanguageCode;
    }

    public List<String> getTexts() {
        return texts;
    }

    public String getFolderId() {
        return folderId;
    }

    public String getTargetLanguageCode() {
        return targetLanguageCode;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public void setTargetLanguageCode(String targetLanguageCode) {
        this.targetLanguageCode = targetLanguageCode;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }
}
