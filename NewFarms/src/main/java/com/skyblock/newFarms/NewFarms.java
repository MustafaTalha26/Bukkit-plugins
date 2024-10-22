package com.skyblock.newFarms;

import com.skyblock.newFarms.EndPortalRefresher.EndPortalHandler;
import com.skyblock.newFarms.EndPortalRefresher.UsableBlockInfo;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class NewFarms extends JavaPlugin {

    FileConfiguration config;
    EndPortalHandler endPortalHandler;
    @Override
    public void onEnable() {
        endPortalHandler = new EndPortalHandler();
        this.saveDefaultConfig();
        config = this.getConfig();
        Map<String,Object> map = Objects.requireNonNull(config.getConfigurationSection("blocks")).getValues(true);
        List<UsableBlockInfo> blocks = new ArrayList<>();
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            UsableBlockInfo block = new UsableBlockInfo();
            block.blockType = entry.getKey();
            block.refreshTime = (int) entry.getValue();
            blocks.add(block);
        }
        endPortalHandler.blocks = blocks;

        config.options().copyDefaults(true);
        saveConfig();

        Bukkit.getLogger().info("                                  ");
        Bukkit.getLogger().info("                                  ");
        Bukkit.getLogger().info("            New Farms!            ");
        Bukkit.getLogger().info("                                  ");
        Bukkit.getLogger().info("                                  ");
        getServer().getPluginManager().registerEvents(endPortalHandler,this);
    }

    @Override
    public void onDisable() {
        this.saveDefaultConfig();
    }
}
