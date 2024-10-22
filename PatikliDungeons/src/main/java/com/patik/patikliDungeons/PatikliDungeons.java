package com.patik.patikliDungeons;

import com.patik.patikliDungeons.Dungeon.Dungeon;
import com.patik.patikliDungeons.Dungeon.DungeonHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public final class PatikliDungeons extends JavaPlugin {

    static File json = new File("dungeons.JSON");
    public Dungeon[] dungeons;

    @Override
    public void onEnable() {
        DungeonHandler dungeonHandler = new DungeonHandler();
        Bukkit.getLogger().info("                                   ");
        Bukkit.getLogger().info("                                   ");
        Bukkit.getLogger().info("         PatikliDungeons!          ");
        Bukkit.getLogger().info("                                   ");
        Bukkit.getLogger().info("                                   ");
        getServer().getPluginManager().registerEvents(dungeonHandler,this);
        try {
            configureDungeons();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void configureDungeons() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("plugins\\dungeons.json"));
        JSONArray jo = (JSONArray) obj;
        dungeons = new Dungeon[jo.size()];

        for (int x = 0; x < jo.size(); x++) {
            JSONObject dungeonObj = (JSONObject) jo.get(x);

            int[] tpNumbers = new int[3];
            int[] dpNumbers = new int[3];

            String text = dungeonObj.get("teleporterCords").toString();
            text = text.substring(1,text.length()-1);
            String[] tokens = text.split(",");
            tpNumbers[0] = Integer.parseInt(tokens[0]);
            tpNumbers[1] = Integer.parseInt(tokens[1]);
            tpNumbers[2] = Integer.parseInt(tokens[2]);

            text = dungeonObj.get("dungeonCords").toString();
            text = text.substring(1,text.length()-1);
            tokens = text.split(",");
            dpNumbers[0] = Integer.parseInt(tokens[0]);
            dpNumbers[1] = Integer.parseInt(tokens[1]);
            dpNumbers[2] = Integer.parseInt(tokens[2]);

            text = dungeonObj.get("commands").toString();
            text = text.substring(1,text.length()-1);
            text = text.replace("\"","");
            tokens = text.split(",");
            List<String> commands = new ArrayList<>(Arrays.asList(tokens));

            int time = Integer.parseInt(dungeonObj.get("time").toString());
            int tier = Integer.parseInt(dungeonObj.get("tier").toString());

            dungeons[x] = new Dungeon();
            dungeons[x].setTeleporterCords(tpNumbers);
            dungeons[x].setDungeonCords(dpNumbers);
            dungeons[x].setCommands(commands);
            dungeons[x].setTime(time);
            dungeons[x].setTier(tier);
            dungeons[x].setActive(false);

            Bukkit.getLogger().info(dungeons[x].toString());
        }
    }
}
