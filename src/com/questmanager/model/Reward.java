package com.questmanager.model;

public class Reward {

    private int level;
    private String title;

    public Reward(int level, String title) {
        this.level = level;
        this.title = title;
    }

    public int getLevel() { return level; }
    public String getTitle() { return title; }
    
}
