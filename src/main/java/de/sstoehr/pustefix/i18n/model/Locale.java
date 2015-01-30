package de.sstoehr.pustefix.i18n.model;

public class Locale {

    private final String localeId;

    public Locale(final String localeId) {
        if (localeId == null) {
            throw new IllegalArgumentException("localeId has to be specified");
        }
        this.localeId = localeId;
    }

    public String getLocaleId() {
        return localeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Locale locale = (Locale) o;

        if (!localeId.equals(locale.localeId)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return localeId.hashCode();
    }

    public String toString() {
        return localeId;
    }
}
