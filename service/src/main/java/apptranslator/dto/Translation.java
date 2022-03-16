package apptranslator.dto;

public class Translation {
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
