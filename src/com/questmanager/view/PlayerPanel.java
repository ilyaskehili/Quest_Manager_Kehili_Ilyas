package com.questmanager.view;

import com.questmanager.controller.PlayerController;
import com.questmanager.exception.PlayerNotFoundException;
import com.questmanager.model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

    private final PlayerController playerController;
    private JLabel nameLabel;
    private JLabel levelLabel;
    private JLabel titleLabel;
    private JProgressBar xpBar;

    public PlayerPanel(PlayerController playerController) {
        this.playerController = playerController;
        setupPanel();
        refresh();
    }

    private void setupPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        setBorder(BorderFactory.createTitledBorder("Profil du joueur"));
        setBackground(new Color(240, 240, 240));

        nameLabel = new JLabel();
        levelLabel = new JLabel();
        titleLabel = new JLabel();
        xpBar = new JProgressBar(0, 100);
        xpBar.setStringPainted(true);
        xpBar.setPreferredSize(new Dimension(200, 20));

        add(nameLabel);
        add(levelLabel);
        add(titleLabel);
        add(new JLabel("XP :"));
        add(xpBar);
    }

    public void refresh() {
        try {
            Player player = playerController.getPlayer();
            nameLabel.setText("Aventurier : " + player.getName());
            levelLabel.setText("Niveau : " + player.getLevel());
            titleLabel.setText("Titre : " + player.getTitle());
            xpBar.setValue(player.getCurrentXP());
            xpBar.setString(player.getCurrentXP() + " / 100 XP");
        } catch (PlayerNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
