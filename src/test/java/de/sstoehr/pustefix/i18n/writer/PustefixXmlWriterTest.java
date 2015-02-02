package de.sstoehr.pustefix.i18n.writer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import de.sstoehr.pustefix.i18n.model.Locale;
import de.sstoehr.pustefix.i18n.model.Message;
import de.sstoehr.pustefix.i18n.model.MessageTranslation;
import de.sstoehr.pustefix.i18n.output.PustefixXmlWriter;
import de.sstoehr.pustefix.i18n.output.Writer;

public class PustefixXmlWriterTest {

    @Test
    public void test() throws IOException {
        Locale l1 = new Locale("l1");
        Locale l2 = new Locale("l2");
        Locale l3 = new Locale("l3");

        File f1 = File.createTempFile("pustefixxml", "f1");
        f1.deleteOnExit();
        File f2 = File.createTempFile("pustefixxml", "f2");
        f2.deleteOnExit();

        List<Message> messages = new ArrayList<>();

        Message m1 = new Message("m1");
        m1.add(new MessageTranslation(l1, "m1l1"));
        m1.add(new MessageTranslation(l3, "m1l3"));

        Message m2 = new Message("m2");
        m2.add(new MessageTranslation(l1, "m2l1"));
        m2.add(new MessageTranslation(l2, "m2l2"));
        m2.add(new MessageTranslation(l3, "m2l3"));

        Message m3 = new Message("m3");
        m3.add(new MessageTranslation(l1, "m3l1"));

        messages.add(m1);
        messages.add(m2);
        messages.add(m3);

        Writer writer = new PustefixXmlWriter();

        writer.addLocale(l1, f1);
        writer.addLocale(l2, f1);
        writer.addLocale(l3, f2);

        writer.write(messages);

        String file1 = FileUtils.readFileToString(f1);
        String file2 = FileUtils.readFileToString(f2);

        Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
          + "<include_part>\n"
          + "  <!--This file is generated. Do not edit manually.-->\n"
          + "  <part name=\"m1\">\n"
          + "    <theme lang=\"l1\" name=\"default\">m1l1</theme>\n"
          + "    <theme name=\"default\"/>\n"
          + "  </part>\n"
          + "  <part name=\"m2\">\n"
          + "    <theme lang=\"l1\" name=\"default\">m2l1</theme>\n"
          + "    <theme lang=\"l2\" name=\"default\">m2l2</theme>\n"
          + "    <theme name=\"default\"/>\n"
          + "  </part>\n"
          + "  <part name=\"m3\">\n"
          + "    <theme lang=\"l1\" name=\"default\">m3l1</theme>\n"
          + "    <theme name=\"default\"/>\n"
          + "  </part>\n"
          + "</include_part>\n", file1);
        Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
          + "<include_part>\n"
          + "  <!--This file is generated. Do not edit manually.-->\n"
          + "  <part name=\"m1\">\n"
          + "    <theme lang=\"l3\" name=\"default\">m1l3</theme>\n"
          + "    <theme name=\"default\"/>\n"
          + "  </part>\n"
          + "  <part name=\"m2\">\n"
          + "    <theme lang=\"l3\" name=\"default\">m2l3</theme>\n"
          + "    <theme name=\"default\"/>\n"
          + "  </part>\n"
          + "  <part name=\"m3\">\n"
          + "    <theme name=\"default\"/>\n"
          + "  </part>\n"
          + "</include_part>\n", file2);
    }

}
