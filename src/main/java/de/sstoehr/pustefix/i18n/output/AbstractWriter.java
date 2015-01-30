package de.sstoehr.pustefix.i18n.output;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import de.sstoehr.pustefix.i18n.model.Locale;

public abstract class AbstractWriter implements Writer {

    protected Map<Locale, File> locales = new LinkedHashMap<>();

    @Override
    public void addLocale(Locale locale, File file) {
        locales.put(locale, file);
    }

}
