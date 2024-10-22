package com.patik.patikliAuction;

import com.patik.patikliAuction.database.sqliteDB;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class PatikliAuction extends JavaPlugin {
    
    @Override
    public void onEnable() {
        sqliteDB db = new sqliteDB();
        db.setupDatabase();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
