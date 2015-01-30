package de.sstoehr.pustefix.i18n.model;

import org.junit.Assert;
import org.junit.Test;
public class MessageTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        new Message(null);
    }

    @Test
    public void test() {
        Message m1 = new Message("m");
        Message m2 = new Message("m");
        Message p = new Message("p");

        Assert.assertEquals("m", m1.getMessageId());
        Assert.assertEquals("m", m2.getMessageId());
        Assert.assertEquals("p", p.getMessageId());

        Assert.assertTrue(m1.equals(m2));
        Assert.assertTrue(m2.equals(m1));
        Assert.assertTrue(m1.equals(m1));
        Assert.assertFalse(p.equals(m1));
        Assert.assertFalse(m1.equals(p));
        Assert.assertFalse(m1.equals(null));

        Assert.assertEquals(m1.hashCode(), m2.hashCode());
    }

    @Test
    public void testTranslations() {
        Message m = new Message("m");

        Assert.assertNotNull(m.getTranslations());
        Assert.assertEquals(0, m.getTranslations().size());

        m.add(new MessageTranslation(new Locale("de"), "translation"));

        Assert.assertEquals(1, m.getTranslations().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTranslationsNull() {
        Message m = new Message("m");
        m.add(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testTranslationsUnmodifiable() {
        Message m = new Message("m");
        MessageTranslation translation = new MessageTranslation(new Locale("de"), "translation");

        m.getTranslations().add(translation);
    }
}
