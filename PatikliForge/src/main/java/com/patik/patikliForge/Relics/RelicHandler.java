package com.patik.patikliForge.Relics;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class RelicHandler implements Listener {
    @EventHandler
    public void onFoodEat(PlayerItemConsumeEvent event) {
        if(event.getItem().getType().isEdible()) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerSaturation(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onNaturalRegenerate(EntityRegainHealthEvent event) {
        if(event.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.SATIATED) ||
                event.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.EATING) ) {
            if(event.getEntityType().equals(EntityType.PLAYER)) {
                Player player = ((Player)event.getEntity());
            }
        }
    }

    @EventHandler
    public void onRightonRelic(PlayerInteractEvent event) {
        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                && event.getPlayer().getCooldown(event.getPlayer().getInventory().getItemInMainHand().getType()) == 0
                && event.getPlayer().getInventory().getItemInMainHand().getType().isEdible()
                && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
            event.setCancelled(true);
            ItemStack relic = event.getPlayer().getInventory().getItemInMainHand();
            if(!relic.getItemMeta().hasDisplayName() || !relic.getItemMeta().hasLore()) {
                return;
            }
            String relicTitle = ((TextComponent) Objects.requireNonNull(relic.getItemMeta().displayName())).content();
            List<String> relicText = new ArrayList<>();
            Map<String,Integer[]> needs = new HashMap<>();
            if(relicTitle.contains("Relic")) {
                List<Component> relicLore = relic.getItemMeta().lore();
                for(int x = 0; x < Objects.requireNonNull(relicLore).size(); x++) {
                    relicText.add(((TextComponent) relicLore.get(x)).content());
                }
            }
            for (String text : relicText) {
                if(text.charAt(0) == '-') {
                    text = text.substring(2);
                    String[] tokens = text.split(" ");
                    int num1 = Integer.parseInt(tokens[0]);
                    int num2 = Integer.parseInt(tokens[2]);
                    Integer[] nums = {num1,num2};
                    needs.put(tokens[1], nums);
                }
            }
            for (Map.Entry<String,Integer[]> entry: needs.entrySet()) {
                event.getPlayer().addPotionEffect(Objects.requireNonNull(PotionEffectType.getByName(entry.getKey())).createEffect(entry.getValue()[1]*20,entry.getValue()[0]-1));
            }
            Material itemType = event.getPlayer().getInventory().getItemInMainHand().getType();
            event.getPlayer().setCooldown(itemType,relic.getEnchantmentLevel(Enchantment.DURABILITY)*20);
        }
    }
}
