package com.javakaian.shooter;


import com.javakaian.shooter.shapes.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/// Singleton Klase Scoreboard kuri
/// privatus konstruktorius, isorines klases negali sukurti naujo scoreboardo
/// statinis instance kuris bus vienintelis zaidime
/// globalus access pointas gauti scoreboardui
/// tasku pridejimo metodas.
///
public class Scoreboard {
    private static volatile Scoreboard instance;
    private Map<String, Integer> playerScores;

    // Private constructor for Singleton pattern
    private Scoreboard() {
        playerScores = new HashMap<>();
    }

    public void update(List<Player> activePlayers) {

        for (Player player : activePlayers) {
            if(!player.isAlive()){
                removePlayer(player.getId());
            }
        }
    }

    // Singleton access method
    public static Scoreboard getInstance() {
        if (instance == null) {
            synchronized (Scoreboard.class) {
                if (instance == null) {
                    instance = new Scoreboard();
                }
            }
        }
        return instance;
    }

    // Add a new player to the scoreboard with an initial score
    public void addPlayer(String playerName) {
        if (!playerScores.containsKey(playerName)) {
            playerScores.put(playerName, 0); // Start with a score of 0
        }
    }

    // Update a player's score by adding points
    public void updateScore(String playerName, int points) {
        if (playerScores.containsKey(playerName)) {
            playerScores.put(playerName, playerScores.get(playerName) + points);
        } else {
            // Optionally, you could add the player automatically if they don't exist
            playerScores.put(playerName, points);
        }
    }

    // Retrieve the current scores, typically to send to clients
    public Map<String, Integer> getScores() {
        return new HashMap<>(playerScores); // Return a copy to avoid external modification
    }
    public void removePlayer(int playerId) {
        String playerName = "Player_" + playerId;
        playerScores.remove(playerName);

    }
}
