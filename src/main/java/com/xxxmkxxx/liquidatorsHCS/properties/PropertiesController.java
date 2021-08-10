package com.xxxmkxxx.liquidatorsHCS.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesController {
    private String pathToProperties = "";
    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    public void safeProperties() {
        try {
            properties.store(new FileOutputStream(pathToProperties), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectProperties() {
        try(FileInputStream fileInputStream = new FileInputStream(pathToProperties)) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PropertiesController (String path) {
        pathToProperties = path;

        connectProperties();
    }
}
