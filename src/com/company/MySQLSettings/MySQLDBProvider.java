package com.company.MySQLSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDBProvider implements DBConnectionProvider{
    private static final String prefix = "com.mysql.jdbc.Driver";

    public MySQLDBProvider(String path) {
        this.connectionString = prefix + path;
    }

    private final String connectionString;

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }
}