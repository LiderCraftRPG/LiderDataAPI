package com.lidercraft.liderdataapi.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface Database {

    Connection getConnection() throws SQLException;

    boolean createTable(String tableName, Map<String, String> columns);

}
