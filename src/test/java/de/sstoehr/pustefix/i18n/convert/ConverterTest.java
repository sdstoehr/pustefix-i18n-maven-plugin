package de.sstoehr.pustefix.i18n.convert;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.sstoehr.pustefix.i18n.input.Reader;
import de.sstoehr.pustefix.i18n.model.Locale;
import de.sstoehr.pustefix.i18n.model.Message;
import de.sstoehr.pustefix.i18n.output.Writer;
public class ConverterTest {

    @Test
    public void test() {
        Reader reader = mock(Reader.class);
        Writer writer = mock(Writer.class);

        List<Message> messages = new ArrayList<>();
        when(reader.read()).thenReturn(messages);

        Converter converter = new Converter(reader, writer);

        Locale locale = new Locale("de_DE");
        File input = new File("input");
        File output = new File("output");

        converter.addLocale(locale, input, output);

        verify(reader, times(1)).addLocale(locale, input);
        verify(writer, times(1)).addLocale(locale, output);

        converter.convert();

        verify(reader, times(1)).read();
        verify(writer, times(1)).write(messages);
    }

}
