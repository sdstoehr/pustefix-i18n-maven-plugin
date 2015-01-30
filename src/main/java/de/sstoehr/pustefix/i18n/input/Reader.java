package de.sstoehr.pustefix.i18n.input;

import java.io.File;
import java.util.List;

import de.sstoehr.pustefix.i18n.model.Locale;
import de.sstoehr.pustefix.i18n.model.Message;

public interface Reader {

    public void addLocale(Locale locale, File file);

    public List<Message> read();

}
