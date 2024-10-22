package com.patik.patikliResource.Block;

import com.patik.patikliResource.PatikliResource;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class BlockHandler implements Listener {

    private final PatikliResource PLUGIN = PatikliResource.getPlugin(PatikliResource.class);

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        int theBlock = 0;
        int flag = 0;
        for (int x = 0; x < PLUGIN.blocks.length; x++) {
            if (event.getBlock().getX() == PLUGIN.blocks[x].getX()
                    && event.getBlock().getY() == PLUGIN.blocks[x].getY()
                    && event.getBlock().getZ() == PLUGIN.blocks[x].getZ()) {
                flag = 1;
                theBlock = x;
            }
        }
        if (flag == 0) {
            return;
        }
        if (event.getPlayer().hasCooldown(event.getPlayer().getInventory().getItemInMainHand().getType())){
            event.getPlayer().sendMessage("§7Bekleme süresi henüz bitmedi");
            event.setCancelled(true);
        }
        else {
            int blockType = 4;
            int blockType2 = 1;
            int blockPower = 0;
            int toolType = 4;
            int toolPower = 0;

            Material special = event.getBlock().getBlockData().getMaterial();

            blockPower = switch (special) {
                case COAL_ORE, DEEPSLATE_COAL_ORE -> {
                    blockType = 0;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_COAL_ORE ? 2 : 1;
                    yield 1;
                }
                case COPPER_ORE, DEEPSLATE_COPPER_ORE -> {
                    blockType = 0;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_COPPER_ORE ? 2 : 1;
                    yield 2;
                }
                case IRON_ORE, DEEPSLATE_IRON_ORE -> {
                    blockType = 0;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_IRON_ORE ? 2 : 1;
                    yield 3;
                }
                case GOLD_ORE, DEEPSLATE_GOLD_ORE -> {
                    blockType = 0;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_GOLD_ORE ? 2 : 1;
                    yield 4;
                }
                case REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE -> {
                    blockType = 0;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_REDSTONE_ORE ? 2 : 1;
                    yield 5;
                }
                case LAPIS_ORE, DEEPSLATE_LAPIS_ORE -> {
                    blockType = 0;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_LAPIS_ORE ? 2 : 1;
                    yield 6;
                }
                case EMERALD_ORE, DEEPSLATE_EMERALD_ORE -> {
                    blockType = 0;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_EMERALD_ORE ? 2 : 1;
                    yield 7;
                }
                case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE -> {
                    blockType = 0;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.DEEPSLATE_DIAMOND_ORE ? 2 : 1;
                    yield 8;
                }
                case AMETHYST_BLOCK, BUDDING_AMETHYST -> {
                    blockType = 0;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.BUDDING_AMETHYST ? 2 : 1;
                    yield 9;
                }
                case STRIPPED_BIRCH_LOG, BIRCH_LOG -> {
                    blockType = 1;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.BIRCH_LOG ? 2 : 1;
                    yield 1;
                }
                case STRIPPED_OAK_LOG, OAK_LOG -> {
                    blockType = 1;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.OAK_LOG ? 2 : 1;
                    yield 2;
                }
                case STRIPPED_JUNGLE_LOG, JUNGLE_LOG -> {
                    blockType = 1;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.JUNGLE_LOG ? 2 : 1;
                    yield 3;
                }
                case STRIPPED_ACACIA_LOG, ACACIA_LOG -> {
                    blockType = 1;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.ACACIA_LOG ? 2 : 1;
                    yield 4;
                }
                case STRIPPED_SPRUCE_LOG, SPRUCE_LOG -> {
                    blockType = 1;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.SPRUCE_LOG ? 2 : 1;
                    yield 5;
                }
                case STRIPPED_DARK_OAK_LOG, DARK_OAK_LOG -> {
                    blockType = 1;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.DARK_OAK_LOG ? 2 : 1;
                    yield 6;
                }
                case STRIPPED_MANGROVE_LOG, MANGROVE_LOG -> {
                    blockType = 1;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.MANGROVE_LOG ? 2 : 1;
                    yield 7;
                }
                case STRIPPED_WARPED_STEM, WARPED_STEM -> {
                    blockType = 1;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.WARPED_STEM ? 2 : 1;
                    yield 8;
                }
                case STRIPPED_CRIMSON_STEM, CRIMSON_STEM -> {
                    blockType = 1;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.CRIMSON_STEM ? 2 : 1;
                    yield 9;
                }
                case ROOTED_DIRT, COARSE_DIRT -> {
                    blockType = 2;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.COARSE_DIRT ? 2 : 1;
                    yield 1;
                }
                case GRAVEL, LIGHT_GRAY_CONCRETE_POWDER -> {
                    blockType = 2;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.LIGHT_GRAY_CONCRETE_POWDER ? 2 : 1;
                    yield 2;
                }
                case PODZOL, MYCELIUM -> {
                    blockType = 2;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.MYCELIUM ? 2 : 1;
                    yield 3;
                }
                case SAND, RED_SAND -> {
                    blockType = 2;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.RED_SAND ? 2 : 1;
                    yield 4;
                }
                case MUD, MUDDY_MANGROVE_ROOTS -> {
                    blockType = 2;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.MUDDY_MANGROVE_ROOTS ? 2 : 1;
                    yield 5;
                }
                case YELLOW_CONCRETE_POWDER, RED_CONCRETE_POWDER -> {
                    blockType = 2;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.RED_CONCRETE_POWDER ? 2 : 1;
                    yield 6;
                }
                case SOUL_SAND, SOUL_SOIL -> {
                    blockType = 2;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.SOUL_SOIL? 2 : 1;
                    yield 7;
                }
                case PACKED_MUD, CLAY -> {
                    blockType = 2;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.CLAY ? 2 : 1;
                    yield 8;
                }
                case LIGHT_BLUE_CONCRETE_POWDER, CYAN_CONCRETE_POWDER -> {
                    blockType = 2;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.CYAN_CONCRETE_POWDER ? 2 : 1;
                    yield 9;
                }
                case BROWN_MUSHROOM, RED_MUSHROOM -> {
                    blockType = 3;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.RED_MUSHROOM ? 2 : 1;
                    yield 1;
                }
                case DANDELION, POPPY -> {
                    blockType = 3;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.POPPY ? 2 : 1;
                    yield 2;
                }
                case BLUE_ORCHID, CORNFLOWER -> {
                    blockType = 3;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.CORNFLOWER ? 2 : 1;
                    yield 3;
                }
                case PINK_TULIP, WHITE_TULIP -> {
                    blockType = 3;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.WHITE_TULIP ? 2 : 1;
                    yield 4;
                }
                case OXEYE_DAISY, ALLIUM -> {
                    blockType = 3;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.ALLIUM ? 2 : 1;
                    yield 5;
                }
                case LILY_OF_THE_VALLEY, AZURE_BLUET -> {
                    blockType = 3;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.AZURE_BLUET ? 2 : 1;
                    yield 6;
                }
                case PEONY, LILAC -> {
                    blockType = 3;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.LILAC ? 2 : 1;
                    yield 7;
                }
                case WARPED_FUNGUS, CRIMSON_FUNGUS -> {
                    blockType = 3;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.CRIMSON_FUNGUS ? 2 : 1;
                    yield 8;
                }
                case DEAD_BUSH, WITHER_ROSE -> {
                    blockType = 3;
                    blockType2 = event.getBlock().getBlockData().getMaterial() == Material.WITHER_ROSE ? 2 : 1;
                    yield 9;
                }
                default -> blockPower;
            };

            toolPower = switch (event.getPlayer().getInventory().getItemInMainHand().getType()) {
                case WOODEN_PICKAXE -> {
                    toolType = 0;
                    yield 0;
                }
                case STONE_PICKAXE -> {
                    toolType = 0;
                    yield 1;
                }
                case IRON_PICKAXE -> {
                    toolType = 0;
                    yield 2;
                }
                case GOLDEN_PICKAXE -> {
                    toolType = 0;
                    yield 3;
                }
                case DIAMOND_PICKAXE -> {
                    toolType = 0;
                    yield 4;
                }
                case NETHERITE_PICKAXE -> {
                    toolType = 0;
                    yield 5;
                }
                case WOODEN_AXE -> {
                    toolType = 1;
                    yield 0;
                }
                case STONE_AXE -> {
                    toolType = 1;
                    yield 1;
                }
                case IRON_AXE -> {
                    toolType = 1;
                    yield 2;
                }
                case GOLDEN_AXE -> {
                    toolType = 1;
                    yield 3;
                }
                case DIAMOND_AXE -> {
                    toolType = 1;
                    yield 4;
                }
                case NETHERITE_AXE -> {
                    toolType = 1;
                    yield 5;
                }
                case WOODEN_SHOVEL -> {
                    toolType = 2;
                    yield 0;
                }
                case STONE_SHOVEL -> {
                    toolType = 2;
                    yield 1;
                }
                case IRON_SHOVEL -> {
                    toolType = 2;
                    yield 2;
                }
                case GOLDEN_SHOVEL -> {
                    toolType = 2;
                    yield 3;
                }
                case DIAMOND_SHOVEL -> {
                    toolType = 2;
                    yield 4;
                }
                case NETHERITE_SHOVEL -> {
                    toolType = 2;
                    yield 5;
                }
                case WOODEN_HOE -> {
                    toolType = 3;
                    yield 0;
                }
                case STONE_HOE -> {
                    toolType = 3;
                    yield 1;
                }
                case IRON_HOE -> {
                    toolType = 3;
                    yield 2;
                }
                case GOLDEN_HOE -> {
                    toolType = 3;
                    yield 3;
                }
                case DIAMOND_HOE -> {
                    toolType = 3;
                    yield 4;
                }
                case NETHERITE_HOE -> {
                    toolType = 3;
                    yield 5;
                }
                default -> toolPower;
            };

            if(toolType == blockType && blockType != 4) {
                if((toolPower == 0 && blockPower <= 1) || (toolPower == 1 && blockPower <= 2)||
                        (toolPower == 2 && blockPower <= 4 && blockPower > 1) ||
                        (toolPower == 3 && blockPower <= 6 && blockPower > 3) ||
                        (toolPower == 4 && blockPower <= 8 && blockPower > 5) ||
                        (toolPower == 5 && blockPower > 6) ) {
                    event.setCancelled(true);
                    String blockName = "t"+blockPower;
                    blockName = switch (blockType) {
                        case 0 -> blockName + "ore" + blockType2;
                        case 1 -> blockName + "wood"+ blockType2;
                        case 2 -> blockName + "dig"+ blockType2;
                        case 3 -> blockName + "plant"+ blockType2;
                        default -> blockName;
                    };
                    Map<Enchantment,Integer> enchants = event.getPlayer().getInventory().getItemInMainHand().getEnchantments();
                    int levelEfficieny = 0;
                    int levelFortune = 0;
                    for(Map.Entry<Enchantment,Integer> ench : enchants.entrySet()) {
                        if(ench.getKey().equals(Enchantment.DIG_SPEED)) {
                            levelEfficieny = ench.getValue();
                        }
                        if(ench.getKey().equals(Enchantment.LOOT_BONUS_BLOCKS)) {
                            levelFortune = ench.getValue();
                        }
                    }
                    if (levelEfficieny == 0) {
                        return;
                    }
                    event.getPlayer().setCooldown(event.getPlayer().getInventory().getItemInMainHand().getType(),20*(levelEfficieny+1));
                    Random rand = new Random();
                    int amount = 1;
                    int n = rand.nextInt(100) + 1;
                    if (n < 20) {
                        amount = amount + (levelFortune+1);
                        event.getPlayer().sendMessage("§7Ekstra materyal kazandın");
                    }
                    event.getBlock().setType(Material.BEDROCK);
                    int finalTheBlock = theBlock;
                    PLUGIN.blocks[theBlock].setTask(Bukkit.getScheduler().runTaskLater(PLUGIN, () -> {
                        int number = rand.nextInt(100) + 1;
                        if (number < PLUGIN.blocks[finalTheBlock].getFirstChance()) {
                            event.getBlock().setType(Objects.requireNonNull(Material.getMaterial(PLUGIN.blocks[finalTheBlock].getFirstBlock())));
                        }
                        else {
                            event.getBlock().setType(Objects.requireNonNull(Material.getMaterial(PLUGIN.blocks[finalTheBlock].getSecondBlock())));
                        }
                    }, PLUGIN.blocks[theBlock].getSeconds() * 20L));
                    Bukkit.dispatchCommand(PLUGIN.getServer().getConsoleSender(), "mm items give "+event.getPlayer().getName()+" "+blockName+" "+ amount);
                }
                else {
                    event.getPlayer().sendMessage("§7Doğru aleti kullanmalısın");
                    event.setCancelled(true);
                }
            }
            else {
                event.getPlayer().sendMessage("§7Doğru aleti kullanmalısın");
                event.setCancelled(true);
            }
        }
    }
}
