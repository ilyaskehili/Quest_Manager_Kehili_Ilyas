package com.questmanager.controller;

import com.questmanager.exception.PlayerNotFoundException;
import com.questmanager.model.Player;
import com.questmanager.repository.PlayerRepository;

public class PlayerController {
    
    private final PlayerRepository playerRepository;
    private Player player;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.player = playerRepository.loadPlayer();
    }

     public void addXP(int amount) {
        player.addXP(amount);
        playerRepository.savePlayer(player);
    }

    public Player getPlayer() throws PlayerNotFoundException {
        if (player == null) {
            throw new PlayerNotFoundException("Aucun joueur trouvé.");
        }
        return player;
    }

     public void updatePlayerName(String name) {
        player.setName(name);
        playerRepository.savePlayer(player);
    }
}
