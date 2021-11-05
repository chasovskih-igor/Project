package com.company.MySQLSettings;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnectionProvider {
    Connection getConnection() throws SQLException;
}
