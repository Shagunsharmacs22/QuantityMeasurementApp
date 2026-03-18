package QuantityMeasurementApplication.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private static final ApplicationConfig config = new ApplicationConfig();

    private static final String URL = config.getDbUrl();
    private static final String USER = config.getDbUsername();
    private static final String PASSWORD = config.getDbPassword();

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error getting DB connection", e);
        }
    }
}