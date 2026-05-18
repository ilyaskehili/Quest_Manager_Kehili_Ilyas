package com.questmanager.model;

public class OneTimeQuest extends Quest {
    
    public OneTimeQuest(String title, String description, int xpReward) {
        super(title, description, xpReward);
    }

    @Override
    public void complete() {
        this.status = QuestStatus.DONE;
    }
}
