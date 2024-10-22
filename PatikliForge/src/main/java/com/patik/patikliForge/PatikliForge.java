package com.patik.patikliForge;

import com.patik.patikliForge.Lootbox.Lootbox;
import com.patik.patikliForge.Recipes.RecipeHandler;
import com.patik.patikliForge.Relics.RelicHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PatikliForge extends JavaPlugin {

    @Override
    public void onEnable() {
        RecipeHandler recipeHandler = new RecipeHandler();
        RelicHandler relicHandler = new RelicHandler();
        Lootbox lootbox = new Lootbox();
        Bukkit.getLogger().info("                                   ");
        Bukkit.getLogger().info("                                   ");
        Bukkit.getLogger().info("         PatikliForge!             ");
        Bukkit.getLogger().info("                                   ");
        Bukkit.getLogger().info("                                   ");
        getServer().getPluginManager().registerEvents(recipeHandler, this);
        getServer().getPluginManager().registerEvents(relicHandler, this);
        getServer().getPluginManager().registerEvents(lootbox,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
