package apptranslator.dto;

public class TextToTranslateDto {
    private String text;
    private String targetLang;

    public String getText() {
        return text;
    }

    public String getTargetLang() {
        return targetLang;
    }
}