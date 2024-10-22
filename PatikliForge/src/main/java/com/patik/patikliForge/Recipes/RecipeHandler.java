package com.patik.patikliForge.Recipes;

import com.patik.patikliForge.PatikliForge;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RecipeHandler implements Listener {

    private final PatikliForge PLUGIN = PatikliForge.getPlugin(PatikliForge.class);
    @EventHandler
    public void onRightonAnvilwithPaper(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK && Objects.requireNonNull(event.getClickedBlock()).getType() == Material.ANVIL
                && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.PAPER) {
            event.setCancelled(true);
            ItemStack recipe = event.getPlayer().getInventory().getItemInMainHand();
            if(!recipe.getItemMeta().hasDisplayName() || !recipe.getItemMeta().hasLore()) {
                return;
            }
            String recipeTitle = ((TextComponent) Objects.requireNonNull(recipe.getItemMeta().displayName())).content();
            List<String> recipeText = new ArrayList<>();
            Map<String,Integer> needs = new HashMap<>();
            if(recipeTitle.contains("Recipe")) {
                List<Component> recipeLore = recipe.getItemMeta().lore();
                assert recipeLore != null;
                for(int x = 0; x < Objects.requireNonNull(recipeLore).size(); x++) {
                    recipeText.add(((TextComponent)recipeLore.get(x)).content());
                }
            }
            for (String text : recipeText) {
                if(text.charAt(0) == '-') {
                    text = text.substring(2);
                    String[] tokens = text.split(" ");
                    int number = Integer.parseInt(tokens[0]);
                    needs.put(tokens[1],number);
                }
            }
            boolean flag = true;
            for (Map.Entry<String,Integer> entry: needs.entrySet()) {
                if(!checkItems(event.getPlayer(), entry.getValue(), entry.getKey())) {
                   flag = false;
                }
            }
            if (flag) {
                removeSomething(event.getPlayer(),1,recipeTitle);
                for (Map.Entry<String,Integer> entry: needs.entrySet()) {
                    removeSomething(event.getPlayer(), entry.getValue(), entry.getKey());
                    event.getPlayer().sendMessage(entry.getKey() + " " + entry.getValue());
                }
                Bukkit.dispatchCommand(PLUGIN.getServer().getConsoleSender(),"mm items give "+event.getPlayer().getName() + " " +recipeTitle.substring(7));
            }
            else {
                event.getPlayer().sendMessage("Not enough items");
                return;
            }
            event.getPlayer().updateInventory();
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
