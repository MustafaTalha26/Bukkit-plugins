package com.skyblock.newFarms.EndPortalRefresher;

import com.skyblock.newFarms.NewFarms;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.List;

public class EndPortalHandler implements Listener {
    public List<UsableBlockInfo> blocks;
    private final NewFarms PLUGIN = NewFarms.getPlugin(NewFarms.class);

    @EventHandler
    public void onBreakWithPortal(BlockBreakEvent event) {
        if (event.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.END_PORTAL_FRAME) &&
            !event.getPlayer().isSneaking()) {

            Collection<ItemStack> drops = event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand());
            for (ItemStack drop : drops) {
                event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
            }
            event.getPlayer().giveExp(event.getExpToDrop());

            int flag = 0;
            Material type = event.getBlock().getType();
            int time = 1;
            event.getBlock().setType(Material.AIR);

            for (UsableBlockInfo info : blocks) {
                if (info.blockType.equals(type.name())) {
                    time = info.refreshTime;
                    flag = 1;
                }
            }
            if (flag == 1) {
                BukkitTask task =  new BukkitRunnable() {
                    @Override
                    public void run() {
                        event.getBlock().setType(type);
                    }
                }.runTaskLater(PLUGIN, 20L *time);
            }
        }
    }
}
