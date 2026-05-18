package com.questmanager.model;

public class DailyQuest extends Quest {
   
    public DailyQuest(String title, String description, int xpReward) {
        super(title, description, xpReward);
    }

    @Override
    public void complete() {
        this.status = QuestStatus.DONE; 
    }
}
