# TaskQuest — Gestionnaire de tâches gamifié

Application de bureau Java permettant de gérer ses tâches sous forme de quêtes RPG.
Chaque quête accomplie rapporte de l'XP à un personnage qui monte de niveau et débloque des titres.

## Fonctionnalités

- Créer des quêtes (quotidiennes ou uniques) avec titre, description et récompense XP
- Afficher la liste des quêtes avec leur statut (TODO, IN_PROGRESS, DONE)
- Marquer une quête comme terminée (XP attribuée automatiquement au joueur)
- Supprimer une quête
- Afficher le profil du joueur : nom, niveau, XP, titre, barre de progression
- Persistance des données en JSON (sauvegarde automatique)

## Prérequis

- Java JDK 17 ou supérieur
- Gson 2.10.1 (inclus dans le dossier `lib/`)

## Lancer l'application

Compiler :
```
javac -cp "lib/gson-2.10.1.jar" -d bin -sourcepath src src/com/questmanager/Main.java
```

Lancer :
```
java -cp "bin;lib/gson-2.10.1.jar" com.questmanager.Main
```

## Architecture

Le projet suit une architecture MVC stricte :

```
src/com/questmanager/
├── model/        → logique métier (Player, Quest, DailyQuest, OneTimeQuest, Reward, QuestStatus)
├── view/         → interface graphique Swing (MainWindow, QuestPanel, PlayerPanel)
├── controller/   → coordination modèle/vue (QuestController, PlayerController)
├── repository/   → persistance JSON (QuestRepository, PlayerRepository)
└── exception/    → exceptions métier (InvalidQuestException, PlayerNotFoundException)
```

## Choix techniques

- **Interface graphique** : Java Swing
- **Persistance** : JSON via la bibliothèque Gson 2.10.1
- **Données** : sauvegardées dans `data/quests.json` et `data/player.json`

## Auteur

Kehili Ilyas — Bachelor Informatique B1 — Ynov Campus Rennes — 2025-2026
