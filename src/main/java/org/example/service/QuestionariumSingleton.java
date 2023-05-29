package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionariumSingleton {

    private static Connection connection;
    private static final String name = "postgres";
    private static final String password = "Bob234From876Canada";

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/questionarium", name, password);
        }
        return connection;

    }

}
