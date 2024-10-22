package com.patik.patikliDungeons.Dungeon;

import com.patik.patikliDungeons.PatikliDungeons;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class DungeonHandler implements Listener {

    private final PatikliDungeons PLUGIN = PatikliDungeons.getPlugin(PatikliDungeons.class);
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && Objects.requireNonNull(event.getClickedBlock()).getType() == Material.TARGET) {
            for (Dungeon dg : PLUGIN.dungeons) {
                if (event.getClickedBlock().getX() == dg.getTeleporterCords()[0] &&
                        event.getClickedBlock().getY() == dg.getTeleporterCords()[1] &&
                        event.getClickedBlock().getZ() == dg.getTeleporterCords()[2]) {

                    if(!dg.isActive()) {
                        if ( checkItems(event.getPlayer(),1, "[Tier " + dg.getTier() + "] Zindan Anahtarı")) {
                            removeSomething(event.getPlayer(), 1, "[Tier " + dg.getTier() + "] Zindan Anahtarı");
                            event.getPlayer().teleport(new Location(event.getPlayer().getWorld(),dg.getDungeonCords()[0],
                                    dg.getDungeonCords()[1],dg.getDungeonCords()[2]));
                            dg.setPlayerInside(event.getPlayer());
                            dg.setActive(true);
                            int dungeonTimeLimit = dg.getTime();
                            dg.setTask(Bukkit.getScheduler().runTaskLater(PLUGIN, () -> {
                                if (dg.isActive()) {
                                    event.getPlayer().teleport(new Location(event.getPlayer().getWorld(),0,1,0));
                                    dg.setActive(false);
                                }
                            }, dungeonTimeLimit * 20L));
                        }
                        else {
                            event.getPlayer().sendMessage("§7Tier" +dg.getTier() +" Zindan Anahtarına ihtiyacın var" );
                        }

                    }
                    else {
                        event.getPlayer().sendMessage("§7Bu zindan şuanda dolu.");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player Dplayer = event.getEntity();
        for (Dungeon dg : PLUGIN.dungeons) {
            if(dg.isActive() && dg.getPlayerInside().equals(Dplayer)) {
                Dplayer.teleport(new Location(Dplayer.getWorld(), 0,1,0));
                dg.getTask().cancel();
                dg.setActive(false);
            }
        }
    }
    @EventHandler
    public void onPlayerTP(PlayerTeleportEvent event) {
        for (Dungeon dg : PLUGIN.dungeons) {
            if(dg.getPlayerInside() != null) {
                if(event.getPlayer().equals(dg.getPlayerInside()) && dg.isActive()) {
                    dg.getTask().cancel();
                    dg.setActive(false);
                }
            }
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        for (Dungeon dg : PLUGIN.dungeons) {
            if(dg.getPlayerInside() != null) {
                if(event.getPlayer().equals(dg.getPlayerInside()) && dg.isActive()) {
                    dg.getTask().cancel();
                    dg.setActive(false);
                }
            }
        }
    }

    public void removeSomething(Player p, int amount, String name) {
        int number = amount;
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            ItemStack item = p.getInventory().getItem(i);
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                String displayName = Objects.requireNonNull(item.getItemMeta().displayName()).toString();
                if (displayName.contains(name)) {
                    if (item.getAmount() <= number) {
                        p.getInventory().setItem(i, null);
                        number = number - item.getAmount();
                    } else {
                        item.setAmount(item.getAmount() - number);
                        number = 0;
                    }
                    if(number <= 0) {
                        break;
                    }
                }
            }
        }
    }

    public boolean checkItems(Player p, int amount, String name) {
        boolean flag = false;
        int number = amount;
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            ItemStack item = p.getInventory().getItem(i);
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                String displayName = Objects.requireNonNull(item.getItemMeta().displayName()).toString();
                if (displayName.contains(name)) {
                    if (item.getAmount() <= number) {
                        if(number == item.getAmount()) {
                            flag = true;
                        }
                        number = number - item.getAmount();
                    } else {
                        flag = true;
                        break;
                    }
                    if(amount <= 0) {
                        break;
                    }
                }
            }
        }
        return flag;
    }
}
