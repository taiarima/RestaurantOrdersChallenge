package org.example.config;

import org.example.util.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// This class is not being used. I am leaving it here as this is a practice project.
public class DatabaseConnection {


    public static Connection getConnection() throws SQLException {
        Properties props = ConfigLoader.getProperties();
        String url = props.getProperty("hibernate.connection.url");
        String user = props.getProperty("hibernate.connection.username");
        String password = props.getProperty("hibernate.connection.password");
        return DriverManager.getConnection(url, user, password);
    }
}

//    private static final String PROPERTIES_FILE = "config.properties";
//    private static String url;
//    private static String user;
//    private static String password;
//
//    static {
//        try {
//            loadProperties();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void loadProperties() throws IOException {
//        Properties props = new Properties();
//        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
//            props.load(fis);
//        }
//        url = props.getProperty("db.url");
//        user = props.getProperty("db.user");
//        password = props.getProperty("db.password");
//    }