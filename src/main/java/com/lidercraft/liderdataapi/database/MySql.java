package com.lidercraft.liderdataapi.database;

import com.lidercraft.liderdataapi.LiderDataAPI;
import org.bukkit.configuration.ConfigurationSection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;

public class MySql implements Database {

    private Connection connection;
    private LiderDataAPI liderDataAPI;
    private String dbHost;
    private String dbPort;
    private String dbUser;
    private String dbPass;
    private String dbName;
    private String url;

    public MySql(LiderDataAPI liderDataAPI) {
        this.liderDataAPI = liderDataAPI;

        try {
            setup();
        } catch (Exception e) {
            e.printStackTrace();
            liderDataAPI.getServer().getLogger().log(Level.SEVERE, "[LIDERDATAAPI] - ERRO AO CONFIGURAR BANCO DE DADOS!!!!");
            liderDataAPI.getServer().getPluginManager().disablePlugin(liderDataAPI);
            liderDataAPI.getServer().shutdown();
        }
    }

    private void setup() throws Exception {
        ConfigurationSection dbConfig = liderDataAPI.getConfig().getConfigurationSection("database");

        if(dbConfig == null)
            throw new Exception("--------> Database config not found in LiderDataAPI <--------");

        this.dbHost = dbConfig.getString("host");
        this.dbPort = dbConfig.getString("port");
        this.dbUser = dbConfig.getString("user");
        this.dbPass = dbConfig.getString("pass");
        this.dbName = dbConfig.getString("name");
        this.url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        if(this.dbHost == null || this.dbPort == null || this.dbUser == null || this.dbPass == null || this.dbName == null)
            throw new Exception("--------> Database config error in LiderDataAPI <--------");

        configureConnection();
    }

    public void configureConnection() throws SQLException {
        connection = DriverManager.getConnection(url, dbUser, dbPass);
    }

    @Override
    public Connection getConnection() throws SQLException {
        if(connection.isClosed())
            configureConnection();

        return connection;
    }

    @Override
    public boolean createTable(String tableName, Map<String, String> columns) {
        return false;
    }
}
