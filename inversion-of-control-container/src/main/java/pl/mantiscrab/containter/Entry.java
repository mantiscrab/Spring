package pl.mantiscrab.containter;

import org.springframework.stereotype.Component;

class Entry {
    private final String original;
    private final String translation;

    Entry(String original, String translation) {
        this.original = original;
        this.translation = translation;
    }

    String getOriginal() {
        return original;
    }

    String getTranslation() {
        return translation;
    }

    @Override
    public String toString() {
        return original + ";" + translation;
    }
}