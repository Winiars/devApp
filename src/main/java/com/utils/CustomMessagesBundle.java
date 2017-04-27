package com.utils;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Gruby on 06.04.2017.
 */
public class CustomMessagesBundle  extends ResourceBundle{


    protected static final String BUNDLE_NAME = "error";
    protected static final String BUNDLE_EXTENSION = "properties";
    protected static final String CHARSET = "UTF-8";
    protected static final ResourceBundle.Control UTF8_CONTROL = new CustomResourceBundle.UTF8Control();

    public CustomMessagesBundle() {
        setParent(ResourceBundle.getBundle(BUNDLE_NAME,
                FacesContext.getCurrentInstance().getViewRoot().getLocale(), UTF8_CONTROL));
    }

    @Override
    protected Object handleGetObject(String key) {
        return parent.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }


}
