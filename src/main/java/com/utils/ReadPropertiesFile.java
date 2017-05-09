package com.utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by Gruby on 13.04.2017.
 */
public class ReadPropertiesFile {

    private String result = "";
    private InputStream inputStream;

    public String getPropertyValue(String key) {

        try {
            inputStream = getClass().getResourceAsStream("/error_en.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            result = properties.getProperty(key);
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
