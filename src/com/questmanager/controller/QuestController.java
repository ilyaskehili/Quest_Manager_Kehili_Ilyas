package com.questmanager.controller;

import com.questmanager.exception.InvalidQuestException;
import com.questmanager.model.DailyQuest;
import com.questmanager.model.OneTimeQuest;
import com.questmanager.model.Quest;
import com.questmanager.model.Player;
import com.questmanager.repository.QuestRepository;

import java.util.List;


public class QuestController {
    private static final int MAX_XP = 1000;
    private static final int MAX_TITLE_LENGTH = 50;
    private static final int MAX_DESC_LENGTH = 200;

    private final QuestRepository questRepository;
    private final PlayerController playerController;
    private List<Quest> quests;

    public QuestController(QuestRepository questRepository, PlayerController playerController) {
        this.questRepository = questRepository;
        this.playerController = playerController;
        this.quests = questRepository.loadQuests();
    }

    public void createQuest(String title, String description, int xpReward, boolean isDaily)
        throws InvalidQuestException {
                    if (title == null || title.trim().isEmpty()) {
            throw new InvalidQuestException("Le titre ne peut pas être vide.");
        }
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new InvalidQuestException("Le titre ne doit pas dépasser " + MAX_TITLE_LENGTH + " caractères.");
        }
        if (description.length() > MAX_DESC_LENGTH) {
            throw new InvalidQuestException("La description ne doit pas dépasser " + MAX_DESC_LENGTH + " caractères.");
        }
        if (xpReward <= 0 || xpReward > MAX_XP) {
            throw new InvalidQuestException("L'XP doit être entre 1 et " + MAX_XP + ".");
        }

        Quest quest = isDaily
            ? new DailyQuest(title.trim(), description.trim(), xpReward)
            : new OneTimeQuest(title.trim(), description.trim(), xpReward);

        quests.add(quest);
        questRepository.saveQuests(quests);
    }

    public void completeQuest(Quest quest) {
        quest.complete();
        playerController.addXP(quest.getXpReward());
        questRepository.saveQuests(quests);
    }

     public void deleteQuest(Quest quest) {
        quests.remove(quest);
        questRepository.saveQuests(quests);
    }

     public List<Quest> getAllQuests() {
        return quests;
    }
}
