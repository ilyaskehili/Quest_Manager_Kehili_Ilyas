package com.questmanager.view;

import com.questmanager.controller.QuestController;
import com.questmanager.exception.InvalidQuestException;
import com.questmanager.model.Quest;
import com.questmanager.model.QuestStatus;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuestPanel extends JPanel {

    private final QuestController questController;
    private final PlayerPanel playerPanel;
    private DefaultListModel<String> listModel;
    private JList<String> questList;
    private List<Quest> quests;

    public QuestPanel(QuestController questController, PlayerPanel playerPanel) {
        this.questController = questController;
        this.playerPanel = playerPanel;
        setupPanel();
        refresh();
    }

    private void setupPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Mes Quêtes"));

        listModel = new DefaultListModel<>();
        questList = new JList<>(listModel);
        questList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(questList);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton createButton = new JButton("Créer une quête");
        JButton completeButton = new JButton("Terminer");
        JButton deleteButton = new JButton("Supprimer");

        buttonPanel.add(createButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        createButton.addActionListener(e -> showCreateDialog());
        completeButton.addActionListener(e -> completeSelected());
        deleteButton.addActionListener(e -> deleteSelected());
    }

    public void refresh() {
        quests = questController.getAllQuests();
        listModel.clear();
        for (Quest quest : quests) {
            listModel.addElement("[" + quest.getStatus() + "] " + quest.getTitle() + " — " + quest.getXpReward() + " XP");
        }
    }

    private void showCreateDialog() {
        JTextField titleField = new JTextField(20);
        JTextField descField = new JTextField(20);
        JTextField xpField = new JTextField(5);
        JCheckBox dailyCheck = new JCheckBox("Quête quotidienne");

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.add(new JLabel("Titre :"));
        form.add(titleField);
        form.add(new JLabel("Description :"));
        form.add(descField);
        form.add(new JLabel("XP :"));
        form.add(xpField);
        form.add(new JLabel("Type :"));
        form.add(dailyCheck);

        int result = JOptionPane.showConfirmDialog(this, form, "Nouvelle quête", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int xp = Integer.parseInt(xpField.getText().trim());
                questController.createQuest(titleField.getText(), descField.getText(), xp, dailyCheck.isSelected());
                refresh();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "L'XP doit être un nombre entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (InvalidQuestException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void completeSelected() {
        int index = questList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionne une quête d'abord.", "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Quest quest = quests.get(index);
        if (quest.getStatus() == QuestStatus.DONE) {
            JOptionPane.showMessageDialog(this, "Cette quête est déjà terminée.", "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }
        questController.completeQuest(quest);
        playerPanel.refresh();
        refresh();
    }

    private void deleteSelected() {
        int index = questList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionne une quête d'abord.", "Attention", JOptionPane.WARNING_MESSAGE);
            return;
        }
        questController.deleteQuest(quests.get(index));
        refresh();
    }
}


