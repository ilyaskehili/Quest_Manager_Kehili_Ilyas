package com.questmanager.model;

public class Player {
    private static final int MAX_LEVEL = 6;
    private static final int XP_PER_LEVEL = 100;

    private String name;
    private int level;
    private int currentXP;
    private int totalXP;
    private String title;

    public Player(String name) {
        this.name = name; 
        this.level = 1;
        this.currentXP = 0;
        this.totalXP = 0;
        this.title = "Novice"; 
    }

    public void addXP(int amount) {
        this.currentXP += amount;
        this.totalXP += amount; 

        while (this.currentXP >= XP_PER_LEVEL && this.level < MAX_LEVEL) {
            this.currentXP -= XP_PER_LEVEL;
            this.level++;
            this.title = updateTitle();
        }
    }

    private String updateTitle() {
        switch (this.level) {
            case 1: return "Novice";
            case 2: return "Apprenti";
            case 3: return "Développeur";
            case 4: return "Vétéran";
            case 5: return "Architecte";
            case 6: return "Légende";
            default: return "Novice";
                
        }
    }

    public String getName() {return name;}
    public int getLevel() {return level; }
    public int getCurrentXP() { return currentXP; }
    public int getTotalXP() { return totalXP; }
    public String getTitle() { return title; }

     public void setName(String name) { this.name = name; }
    public void setLevel(int level) { this.level = level; }
    public void setCurrentXP(int currentXP) { this.currentXP = currentXP; }
    public void setTotalXP(int totalXP) { this.totalXP = totalXP; }
    public void setTitle(String title) { this.title = title; }
}
