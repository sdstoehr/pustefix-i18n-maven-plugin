package de.sstoehr.pustefix.i18n.model;

import org.junit.Assert;
import org.junit.Test;
public class MessageTranslationTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNull1() {
        new MessageTranslation(null, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull2() {
        new MessageTranslation(new Locale("de"), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull3() {
        new MessageTranslation(null, null);
    }

    @Test
    public void test() {
        Locale l = new Locale("de");
        MessageTranslation translation = new MessageTranslation(l, "translation");

        Assert.assertEquals(l, translation.getLocale());
        Assert.assertEquals("translation", translation.getTranslation());
    }

    @Test
    public void testEquals() {
        Locale l1 = new Locale("de");
        Locale l2 = new Locale("de");
        Locale l3 = new Locale("it");

        MessageTranslation t1 = new MessageTranslation(l1, "test");
        MessageTranslation t2 = new MessageTranslation(l1, "test");
        MessageTranslation t3 = new MessageTranslation(l2, "test");

        Assert.assertTrue(t1.equals(t1));
        Assert.assertTrue(t1.equals(t2));
        Assert.assertTrue(t2.equals(t3));
        Assert.assertTrue(t3.equals(t1));

        Assert.assertEquals(t1.hashCode(), t2.hashCode());
        Assert.assertEquals(t2.hashCode(), t3.hashCode());

        Assert.assertFalse(t1.equals(null));

        MessageTranslation t4 = new MessageTranslation(l3, "test");
        MessageTranslation t5 = new MessageTranslation(l1, "anothertest");

        Assert.assertFalse(t1.equals(t4));
        Assert.assertFalse(t1.equals(t5));
    }

}
