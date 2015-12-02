package de.sstoehr.pustefix.i18n.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.sstoehr.pustefix.i18n.model.Locale;
import de.sstoehr.pustefix.i18n.model.Message;
import de.sstoehr.pustefix.i18n.model.MessageTranslation;

public class PustefixXmlWriter extends AbstractWriter {

    private final Charset charset;

    public PustefixXmlWriter(Charset charset) {
        this.charset = charset;
    }

    public PustefixXmlWriter() {
        this(Charset.defaultCharset());
    }

    @Override
    public void write(List<Message> messages) {
        Map<File, List<Locale>> files = new HashMap<>();

        for (Map.Entry<Locale, File> entry : locales.entrySet()) {
            File f = entry.getValue();
            Locale l = entry.getKey();

            List<Locale> list = files.get(f);
            if (list == null) {
                list = new ArrayList<>();
                files.put(f, list);
            }

            list.add(l);
        }

        for (Map.Entry<File, List<Locale>> output : files.entrySet()) {
            File f = output.getKey();
            List<Locale> localeList = output.getValue();

            try {
                this.writeSingle(messages, localeList, f);
            } catch (Exception e) {
                throw new RuntimeException("Error writing output", e);
            }
        }
    }

    private void writeSingle(List<Message> messages, List<Locale> localeList, File outputFile)
      throws ParserConfigurationException, TransformerException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc =  db.newDocument();

        Element root = doc.createElement("include_parts");
        doc.appendChild(root);

        Comment comment = doc.createComment("This file is generated. Do not edit manually.");
        root.appendChild(comment);

        for (Message message : messages) {
            Element part = doc.createElement("part");
            root.appendChild(part);

            part.setAttribute("name", message.getMessageId());

            for (MessageTranslation translation : message.getTranslations()) {
                if (!localeList.contains(translation.getLocale())) {
                    continue;
                }

                Element theme = doc.createElement("theme");
                part.appendChild(theme);

                theme.setAttribute("name", "default");
                theme.setAttribute("lang", translation.getLocale().getLocaleId());

                theme.setTextContent(translation.getTranslation());

            }

            Element theme = doc.createElement("theme");
            part.appendChild(theme);
            theme.setAttribute("name", "default");
        }

        File parent = outputFile.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        try (
            FileOutputStream fos = new FileOutputStream(outputFile);
            java.io.Writer writer = new OutputStreamWriter(fos, charset)) {
            StreamResult result = new StreamResult(writer);
            DOMSource source = new DOMSource(doc);

            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer  = transFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            transformer.transform(source, result);
        }

    }
}
