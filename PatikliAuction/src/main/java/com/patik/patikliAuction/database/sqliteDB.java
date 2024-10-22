package com.patik.patikliAuction.database;

import com.patik.patikliAuction.PatikliAuction;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqliteDB {
    private final PatikliAuction PLUGIN = PatikliAuction.getPlugin(PatikliAuction.class);

    public File pFolder;
    public File SQLFile;
    public sqliteDB() {    }

    public void setupDatabase() {
        pFolder = new File("plugins" + File.separator + "PatikliAuction" + File.separator + "databases");
        if (!pFolder.exists()) {
            boolean who = pFolder.mkdirs();
        }
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:"+ pFolder.getAbsolutePath() +"/Auctions.db")) {
            var meta = conn.getMetaData();
            PLUGIN.getLogger().info("The driver name is " + meta.getDriverName());
            PLUGIN.getLogger().info("A new database has been created.");
        } catch (SQLException e) {
            PLUGIN.getLogger().info(e.getMessage());
        }
    }
}
