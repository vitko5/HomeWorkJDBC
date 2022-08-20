package com.homework.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql:jdbc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Qwerty12345";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(POSTGRESQL_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Error. PostgreSQL JDBC Driver is not found.");
            e.printStackTrace();
            return null;
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error. Connection failed.");
            e.printStackTrace();
        }
        return connection;
    }

}
