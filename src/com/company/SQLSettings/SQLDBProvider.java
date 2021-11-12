package com.company.SQLSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDBProvider implements DBConnectionProvider{
    private static final String prefix = "jdbc:sqlite:";

    public SQLDBProvider(String path) {
        this.connectionString = prefix + path;
    }

    private final String connectionString;

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }
}