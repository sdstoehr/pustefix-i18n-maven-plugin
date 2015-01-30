package de.sstoehr.pustefix.i18n.output;

import java.io.File;
import java.util.List;

import de.sstoehr.pustefix.i18n.model.Locale;
import de.sstoehr.pustefix.i18n.model.Message;

public interface Writer {

    public void addLocale(Locale locale, File file);

    public void write(List<Message> messages);

}
