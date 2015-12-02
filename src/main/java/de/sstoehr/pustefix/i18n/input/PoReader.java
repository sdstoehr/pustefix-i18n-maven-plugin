package de.sstoehr.pustefix.i18n.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sstoehr.pustefix.i18n.model.Locale;
import de.sstoehr.pustefix.i18n.model.Message;
import de.sstoehr.pustefix.i18n.model.MessageTranslation;

public class PoReader extends AbstractReader {

    private static final Logger LOG = LoggerFactory.getLogger(PoReader.class);

    private static final String MSGID = "msgid";
    private static final String MSGSTR = "msgstr";

    private final Charset charset;

    public PoReader(Charset charset) {
        this.charset = charset;
    }

    public PoReader() {
        this(Charset.defaultCharset());
    }

    @Override
    void readSingle(Locale locale, File file, Map<String, Message> messages) {

        try {
            String content = FileUtils.readFileToString(file, charset);

            BufferedReader reader = new BufferedReader(new StringReader(content));

            String line, messageId = null, messageStr = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith(MSGID)) {
                    messageId = line.replaceFirst(MSGID, "");
                    messageId = escapeString(messageId);
                } else if (line.startsWith(MSGSTR)) {
                    messageStr = line.replaceFirst(MSGSTR, "");
                    messageStr = escapeString(messageStr);

                    if (messageId != null) {
                        this.addMessage(locale, messageId, messageStr, messages);
                    }

                    messageId = null;
                    messageStr = null;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading po files", e);
        }
    }

    private String escapeString(String input) {
        if (input == null) {
            return null;
        }

        String output = input.trim();
        output = StringEscapeUtils.unescapeJava(output);

        if (output.startsWith("\"") && output.endsWith("\"")) {
            output = output.substring(1, output.length()-1);
        }

        return output;
    }

    private void addMessage(Locale locale, String messageId, String translation, Map<String, Message> messages) {
        LOG.debug("Adding translation for locale {}: {} -> {}", locale, messageId, translation);

        Message message = messages.get(messageId);

        if (message == null) {
            message = new Message(messageId);
            messages.put(messageId, message);
        }

        MessageTranslation messageTranslation = new MessageTranslation(locale, translation);
        message.add(messageTranslation);
    }
}
