package com.example.resttomcathelloworld;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println(new File("").getPath());
            System.out.println(new File("").getAbsolutePath());
            System.out.println("Current working directory: " + System.getProperty("user.dir"));
            connection = DriverManager.getConnection ("jdbc:sqlite:nominatim.db");
            System.out.println("Database connection successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
