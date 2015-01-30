package de.sstoehr.pustefix.i18n.model;

public class MessageTranslation {

    private final Locale locale;
    private final String translation;

    public MessageTranslation(Locale locale, String translation) {
        if (locale == null || translation == null) {
            throw new IllegalArgumentException("locale and translation have to be specified");
        }
        this.locale = locale;
        this.translation = translation;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getTranslation() {
        return translation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessageTranslation that = (MessageTranslation) o;

        if (!locale.equals(that.locale)) {
            return false;
        }
        if (!translation.equals(that.translation)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = locale.hashCode();
        result = 31 * result + translation.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return translation + " (" + locale + ")";
    }
}
