package com.patik.patikliDungeons.Dungeon;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class Dungeon {
    private int[] teleporterCords;
    private int[] dungeonCords;
    private List<String> commands;
    private boolean active;
    private Player playerInside;
    private BukkitTask task;
    private int time;
    private int tier;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public Player getPlayerInside() {
        return playerInside;
    }

    public void setPlayerInside(Player playerInside) {
        this.playerInside = playerInside;
    }

    public BukkitTask getTask() {
        return task;
    }

    public void setTask(BukkitTask task) {
        this.task = task;
    }

    public int[] getDungeonCords() {
        return dungeonCords;
    }

    public void setDungeonCords(int[] dungeonCords) {
        this.dungeonCords = dungeonCords;
    }

    public int[] getTeleporterCords() {
        return teleporterCords;
    }

    public void setTeleporterCords(int[] teleporterCords) {
        this.teleporterCords = teleporterCords;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}