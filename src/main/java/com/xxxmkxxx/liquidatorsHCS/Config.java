package com.xxxmkxxx.liquidatorsHCS;

import com.xxxmkxxx.liquidatorsHCS.properties.PropertiesController;

public class Config {
    private static final String pathToProperties = "src/main/resources/properties/config.properties";
    private static PropertiesController config = new PropertiesController(pathToProperties);

    public static String getField(String nameField) {
        return config.getProperties().getProperty(nameField);
    }

    public static void setField(String nameField, String value) {
        config.getProperties().setProperty(nameField, value);
    }

    public static void safe() {
        config.safeProperties();
    }
}
