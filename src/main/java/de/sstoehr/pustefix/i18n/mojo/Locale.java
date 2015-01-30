package de.sstoehr.pustefix.i18n.mojo;

import java.io.File;

public class Locale {

    private String locale;
    private File poFile;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public File getPoFile() {
        return poFile;
    }

    public void setPoFile(File poFile) {
        this.poFile = poFile;
    }
}
