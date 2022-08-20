package com.homework.jdbc;

import com.homework.jdbc.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConfig.getConnection();
        System.out.println(connection.getMetaData().getURL());
    }
}
