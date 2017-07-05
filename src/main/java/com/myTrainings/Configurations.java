package com.myTrainings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configurations {

    private static Properties config;

    private Configurations() {
    }

    public static Properties getConfig() throws IOException {
        if (Configurations.config == null) {
            config = new Properties();
            FileInputStream fi = new FileInputStream("src/main/resources/config.properties");
            config.load(fi);
        }
        return config;
    }
}
