package com.questmanager.view;

import com.questmanager.controller.PlayerController;
import com.questmanager.controller.QuestController;
import com.questmanager.repository.PlayerRepository;
import com.questmanager.repository.QuestRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    private final QuestController questController;
    private final PlayerController playerController;
    private QuestPanel questPanel;
    private PlayerPanel playerPanel;

    public MainWindow() {
        PlayerRepository playerRepository = new PlayerRepository();
        QuestRepository questRepository = new QuestRepository();
        this.playerController = new PlayerController(playerRepository);
        this.questController = new QuestController(questRepository, playerController);

        setupWindow();
        setupPanels();
        setupCloseHandler();
    }

    private void setupWindow() {
        setTitle("TaskQuest");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void setupPanels() {
        playerPanel = new PlayerPanel(playerController);
        questPanel = new QuestPanel(questController, playerPanel);

        add(playerPanel, BorderLayout.NORTH);
        add(questPanel, BorderLayout.CENTER);
    }

    private void setupCloseHandler() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }
}
