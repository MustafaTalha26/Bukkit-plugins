package com.patik.patikliResource;

import com.patik.patikliResource.Block.Block;
import com.patik.patikliResource.Block.BlockHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public final class PatikliResource extends JavaPlugin {

    public Block[] blocks;

    @Override
    public void onEnable() {
        BlockHandler blockHandler = new BlockHandler();
        Bukkit.getLogger().info("                                   ");
        Bukkit.getLogger().info("                                   ");
        Bukkit.getLogger().info("         PatikliResources!         ");
        Bukkit.getLogger().info("                                   ");
        Bukkit.getLogger().info("                                   ");
        getServer().getPluginManager().registerEvents(blockHandler,this);
        try {
            configureBlocks();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void configureBlocks() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("plugins\\blocks.json"));
        JSONArray jo = (JSONArray) obj;
        blocks = new Block[jo.size()];

        for (int a = 0; a < jo.size(); a++) {
            JSONObject blockObj = (JSONObject) jo.get(a);

            int x = Integer.parseInt(blockObj.get("x").toString());
            int y = Integer.parseInt(blockObj.get("y").toString());
            int z = Integer.parseInt(blockObj.get("z").toString());
            int seconds = Integer.parseInt(blockObj.get("seconds").toString());
            String firstBlock = blockObj.get("firstBlock").toString();
            int firstChance = Integer.parseInt(blockObj.get("firstChance").toString());
            String secondBlock = blockObj.get("secondBlock").toString();
            int secondChance = Integer.parseInt(blockObj.get("secondChance").toString());

            blocks[a] = new Block();
            blocks[a].setX(x);
            blocks[a].setY(y);
            blocks[a].setZ(z);
            blocks[a].setSeconds(seconds);
            blocks[a].setFirstBlock(firstBlock);
            blocks[a].setFirstChance(firstChance);
            blocks[a].setSecondBlock(secondBlock);
            blocks[a].setSecondChance(secondChance);

            Bukkit.getLogger().info(blocks[a].toString());
        }
    }
}
