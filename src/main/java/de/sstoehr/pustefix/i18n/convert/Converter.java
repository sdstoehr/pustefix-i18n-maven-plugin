package de.sstoehr.pustefix.i18n.convert;

import java.io.File;
import java.util.List;

import de.sstoehr.pustefix.i18n.input.Reader;
import de.sstoehr.pustefix.i18n.model.Locale;
import de.sstoehr.pustefix.i18n.model.Message;
import de.sstoehr.pustefix.i18n.output.Writer;

public class Converter {

    private Reader reader;
    private Writer writer;

    public Converter(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void convert() {
        List<Message> messages = reader.read();
        writer.write(messages);
    }

    public void addLocale(Locale locale, File inputFile, File outputFile) {
        reader.addLocale(locale, inputFile);
        writer.addLocale(locale, outputFile);
    }
}
