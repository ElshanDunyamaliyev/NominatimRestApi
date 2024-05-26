package com.example.resttomcathelloworld.db;

import com.example.resttomcathelloworld.exception.InvalidDbCredentialsException;
import com.example.resttomcathelloworld.exception.SqliteClassNotFoundException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SqliteClassNotFoundException("Sqlite class could not loaded");
        }
        try {
            connection = DriverManager.getConnection ("jdbc:sqlite:nominatim.db");
            System.out.println("Database connection successfully");
        } catch (SQLException e) {
            throw new InvalidDbCredentialsException("Database credentials is invalid");
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
