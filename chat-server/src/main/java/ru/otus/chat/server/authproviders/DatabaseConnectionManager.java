package ru.otus.chat.server.authproviders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnectionManager {

    private static final String URL = "jdbc:postgresql://localhost:5432/chat";
    private static final String USERNAME = "pgadmin";
    private static final String PASSWORD = "pgadmin";

    private DatabaseConnectionManager() {
    }

    public static Connection open() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
