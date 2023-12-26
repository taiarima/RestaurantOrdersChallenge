package org.example;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class ConfigLoader {
    private static Properties properties;

    public ConfigLoader() {
        properties = new Properties();
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
