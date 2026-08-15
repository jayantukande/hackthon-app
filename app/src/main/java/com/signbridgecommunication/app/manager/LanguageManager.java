package com.signbridgecommunication.app.manager;

import com.signbridgecommunication.app.data.model.Phrase;

public class LanguageManager {
    public enum Language {
        ENGLISH("en"),
        HINDI("hi"),
        MARATHI("mr");

        private final String code;
        Language(String code) { this.code = code; }
        public String getCode() { return code; }
    }

    private Language currentLanguage = Language.ENGLISH;

    public void setLanguage(Language language) {
        this.currentLanguage = language;
    }

    public Language getCurrentLanguage() {
        return currentLanguage;
    }

    public String getTranslatedText(Phrase phrase) {
        switch (currentLanguage) {
            case HINDI:
                return phrase.getHindiText();
            case MARATHI:
                return phrase.getMarathiText();
            default:
                return phrase.getEnglishText();
        }
    }
}