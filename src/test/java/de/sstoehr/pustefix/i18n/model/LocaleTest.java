package de.sstoehr.pustefix.i18n.model;

import org.junit.Assert;
import org.junit.Test;
public class LocaleTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        new Locale(null);
    }

    @Test
    public void test() {
        Locale de = new Locale("de");
        Locale de2 = new Locale("de");
        Locale it = new Locale("it");

        Assert.assertEquals("de", de.getLocaleId());
        Assert.assertEquals("de", de2.getLocaleId());
        Assert.assertEquals("it", it.getLocaleId());

        Assert.assertTrue(de.equals(de2));
        Assert.assertTrue(de2.equals(de));
        Assert.assertTrue(de.equals(de));
        Assert.assertFalse(it.equals(de));
        Assert.assertFalse(de.equals(it));
        Assert.assertFalse(de.equals(null));
    }
}
