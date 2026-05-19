package com.questmanager.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.questmanager.model.Player;

import java.io.*;

public class PlayerRepository {
    private static final String FILE_PATH = "data/player.json";
    private final Gson gson; 

    public PlayerRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void savePlayer(Player player) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(player, writer);
            } catch (IOException e) {
                throw new RuntimeException("Erreur sauvegarde joueur : " + e.getMessage());
        }
    }

    public Player loadPlayer() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new Player("Aventurier");
        }

        try (Reader reader = new FileReader(FILE_PATH)) {
            Player player = gson.fromJson(reader,Player.class);
            return player != null ? player : new Player("Aventurier");
            } catch (IOException e) {
                throw new RuntimeException("Erreur chargement joueur : " + e.getMessage());
        }
    }
}
