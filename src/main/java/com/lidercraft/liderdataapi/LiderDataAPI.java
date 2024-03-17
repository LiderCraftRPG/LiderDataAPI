package com.lidercraft.liderdataapi;

import com.lidercraft.liderdataapi.database.Database;
import com.lidercraft.liderdataapi.database.MySql;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class LiderDataAPI extends JavaPlugin {

    private static LiderDataAPI instance;

    private static Database database;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        database = new MySql(this);
        instance = this;

    }

    @Override
    public void onDisable() {

        try {
            database.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Database getDatabase() {
        return database;
    }

    public static LiderDataAPI getInstance() {
        return instance;
    }
}
