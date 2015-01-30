package de.sstoehr.pustefix.i18n.input;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.sstoehr.pustefix.i18n.model.Locale;
import de.sstoehr.pustefix.i18n.model.Message;

public abstract class AbstractReader implements Reader {

    protected Map<Locale, File> locales = new LinkedHashMap<>();

    @Override
    public void addLocale(Locale locale, File file) {
        locales.put(locale, file);
    }

    @Override
    public List<Message> read() {
        Map<String, Message> messages = new LinkedHashMap<>();
        for (Map.Entry<Locale, File> locale : locales.entrySet()) {
            this.readSingle(locale.getKey(), locale.getValue(), messages);
        }

        List<Message> list = new ArrayList<>(messages.values());
        return Collections.unmodifiableList(list);
    }

    abstract void readSingle(Locale locale, File file, Map<String, Message> messages);
}
