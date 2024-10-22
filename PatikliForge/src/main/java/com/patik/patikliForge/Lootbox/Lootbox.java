package com.patik.patikliForge.Lootbox;

import com.patik.patikliForge.PatikliForge;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class Lootbox implements Listener {

    private final PatikliForge PLUGIN = PatikliForge.getPlugin(PatikliForge.class);

    @EventHandler
    public void onBoxOpen(PlayerInteractEvent event) {
        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.BOOK
                && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
            event.setCancelled(true);
            String itemInfo = Objects.requireNonNull(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().displayName()).toString();
            if (itemInfo.contains("Tarif Kitabı")) {
                String loot = "tarif";
                if (itemInfo.contains("[Tier 1]")) {
                    loot = loot + String.valueOf(1);
                    Bukkit.dispatchCommand(PLUGIN.getServer().getConsoleSender(),"mm items give "+event.getPlayer().getName() + " " +loot);
                    removeSomething(event.getPlayer(),1,"[Tier 1]","Tarif Kitabı");
                }
                else if (itemInfo.contains("[Tier 2]")) {
                    loot = loot + String.valueOf(2);
                    Bukkit.dispatchCommand(PLUGIN.getServer().getConsoleSender(),"mm items give "+event.getPlayer().getName() + " " +loot);
                    removeSomething(event.getPlayer(),1,"[Tier 2]","Tarif Kitabı");
                }
                else if (itemInfo.contains("[Tier 3]")) {
                    loot = loot + String.valueOf(3);
                    Bukkit.dispatchCommand(PLUGIN.getServer().getConsoleSender(),"mm items give "+event.getPlayer().getName() + " " +loot);
                    removeSomething(event.getPlayer(),1,"[Tier 3]","Tarif Kitabı");
                }
                else if (itemInfo.contains("[Tier 4]")) {
                    loot = loot + String.valueOf(4);
                    Bukkit.dispatchCommand(PLUGIN.getServer().getConsoleSender(),"mm items give "+event.getPlayer().getName() + " " +loot);
                    removeSomething(event.getPlayer(),1,"[Tier 4]","Tarif Kitabı");
                }
                else if (itemInfo.contains("[Tier 5]")) {
                    loot = loot + String.valueOf(5);
                    Bukkit.dispatchCommand(PLUGIN.getServer().getConsoleSender(),"mm items give "+event.getPlayer().getName() + " " +loot);
                    removeSomething(event.getPlayer(),1,"[Tier 5]","Tarif Kitabı");
                }
            }
        }
    }

    public void removeSomething(Player p, int amount, String name, String name2) {
        int number = amount;
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            ItemStack item = p.getInventory().getItem(i);
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                String displayName = Objects.requireNonNull(item.getItemMeta().displayName()).toString();
                if (displayName.contains(name) && displayName.contains(name2)) {
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
}
