package com.patik.patikliResource.Block;

import org.bukkit.scheduler.BukkitTask;

public class Block {
    private int x;
    private int y;
    private int z;
    private int seconds;
    private String firstBlock;
    private int firstChance;
    private String secondBlock;
    private int secondChance;
    private BukkitTask task;

    public String getFirstBlock() {
        return firstBlock;
    }

    public void setFirstBlock(String firstBlock) {
        this.firstBlock = firstBlock;
    }

    public int getFirstChance() {
        return firstChance;
    }

    public void setFirstChance(int firstChance) {
        this.firstChance = firstChance;
    }

    public String getSecondBlock() {
        return secondBlock;
    }

    public void setSecondBlock(String secondBlock) {
        this.secondBlock = secondBlock;
    }

    public int getSecondChance() {
        return secondChance;
    }

    public void setSecondChance(int secondChance) {
        this.secondChance = secondChance;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public BukkitTask getTask() {
        return task;
    }

    public void setTask(BukkitTask task) {
        this.task = task;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
