package com.questmanager.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.questmanager.model.DailyQuest;
import com.questmanager.model.OneTimeQuest;
import com.questmanager.model.Quest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuestRepository {

    private static final String FILE_PATH = "data/quests.json";
    private final Gson gson;

    public QuestRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        new File("data").mkdir();
    }

    public void saveQuests(List<Quest> quests) {
        List<DailyQuest> dailyQuests = new ArrayList<>();
        List<OneTimeQuest> oneTimeQuests = new ArrayList<>();

        for (Quest quest : quests) {
            if (quest instanceof DailyQuest) {
                dailyQuests.add((DailyQuest) quest);
            } else if (quest instanceof OneTimeQuest) {
                oneTimeQuests.add((OneTimeQuest) quest);
            }
        }
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(new QuestData(dailyQuests, oneTimeQuests), writer);
            } catch (IOException e) {
                throw new RuntimeException("Erreur sauvegarde quêtes : " + e.getMessage());
        }
    }
    public List<Quest> loadQuests() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(FILE_PATH)) {
            QuestData data = gson.fromJson(reader, QuestData.class);
            List<Quest> quests = new ArrayList<>();
            if (data != null) {
                if (data.dailyQuests != null) quests.addAll(data.dailyQuests);
                if (data.oneTimeQuests != null) quests.addAll(data.oneTimeQuests);
            }
            return quests;
        } catch (IOException e) {
            throw new RuntimeException("Erreur chargement quêtes : " + e.getMessage());
        }
    }

    private static class QuestData {
        List<DailyQuest> dailyQuests;
        List<OneTimeQuest> oneTimeQuests;

        QuestData(List<DailyQuest> d, List<OneTimeQuest> o) {
            this.dailyQuests = d;
            this.oneTimeQuests = o;
        }
    }
}

    

