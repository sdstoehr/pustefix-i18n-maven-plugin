package de.sstoehr.pustefix.i18n.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Message {

    private final String messageId;
    private List<MessageTranslation> translations = new ArrayList<>();

    public Message(final String messageId) {
        if (messageId == null) {
            throw new IllegalArgumentException("messageId has to be specified");
        }
        this.messageId = messageId;
    }

    public void add(MessageTranslation translation) {
        if (translation == null) {
            throw new IllegalArgumentException("translation has to be specified");
        }
        this.translations.add(translation);
    }

    public String getMessageId() {
        return messageId;
    }

    public List<MessageTranslation> getTranslations() {
        List<MessageTranslation> values = new ArrayList<>(translations);
        return Collections.unmodifiableList(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message message = (Message) o;

        if (!messageId.equals(message.messageId)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return messageId.hashCode();
    }
}
