package org.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionariumSingleton {

    private static Connection connection;
    private static final String name = "postgres";
    private static final String password = "Bob234From876Canada";


// добавил исключение при подключении к базе

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/questionarium", name, password);
            }
        } catch (SQLException e) {
            System.err.println("Cannot create connection to db ");
            e.printStackTrace();
            System.exit(1);
        }
        return connection;
    }

}
