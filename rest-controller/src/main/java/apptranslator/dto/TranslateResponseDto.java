package apptranslator.dto;

import java.util.List;

public class TranslateResponseDto {
    private List<Translation> translations;

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public static class Translation {
        private String text;
        private String detectedLanguageCode;

        public String getText() {
            return text;
        }

        public String getDetectedLanguageCode() {
            return detectedLanguageCode;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setDetectedLanguageCode(String detectedLanguageCode) {
            this.detectedLanguageCode = detectedLanguageCode;
        }
    }
}