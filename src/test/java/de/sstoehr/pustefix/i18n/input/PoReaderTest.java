package de.sstoehr.pustefix.i18n.input;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.sstoehr.pustefix.i18n.model.Locale;
import de.sstoehr.pustefix.i18n.model.Message;
import de.sstoehr.pustefix.i18n.model.MessageTranslation;

public class PoReaderTest {

    @Test
    public void test() throws URISyntaxException {

        Reader reader = new PoReader(Charset.forName("UTF-8"));

        Locale l1 = new Locale("de_DE");
        Locale l2 = new Locale("en_GB");
        reader.addLocale(l1, getTestFile("po/de_DE.po"));
        reader.addLocale(l2, getTestFile("po/en_GB.po"));

        List<Message> messages = reader.read();

        Assert.assertNotNull(messages);
        Assert.assertEquals(3, messages.size());

        Message m0 = messages.get(0);
        Message m1 = messages.get(1);
        Message m2 = messages.get(2);

        Assert.assertEquals("test", m0.getMessageId());
        Assert.assertEquals("test2", m1.getMessageId());
        Assert.assertEquals("te\\\"st", m2.getMessageId());

        List<MessageTranslation> m0t = Arrays.asList(
          new MessageTranslation(l1, "This is a test"),
          new MessageTranslation(l2, "EN: This is a test")
        );

        List<MessageTranslation> m1t = Arrays.asList(
          new MessageTranslation(l1, "Another \"test äöü\""),
          new MessageTranslation(l2, "EN: Another \"test\"")
        );

        List<MessageTranslation> m2t = Arrays.asList(
          new MessageTranslation(l1, "")
        );

        Assert.assertEquals(m0t, m0.getTranslations());
        Assert.assertEquals(m1t, m1.getTranslations());
        Assert.assertEquals(m2t, m2.getTranslations());

    }

    private File getTestFile(String path) throws URISyntaxException {
        ClassLoader loader = PoReaderTest.class.getClassLoader();
        URL url = loader.getResource(path);
        if (url == null) {
            return null;
        }
        return new File(url.toURI());
    }
}
