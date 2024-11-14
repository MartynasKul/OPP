package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.HashMap;
import java.util.Map;

// DEPRECATED CLASS

/// Singleton Klase Scoreboard kuri
/// privatus konstruktorius, isorines klases negali sukurti naujo scoreboardo
/// statinis instance kuris bus vienintelis zaidime
/// globalus access pointas gauti scoreboardui
/// tasku pridejimo metodas.
///
public class Scoreboard {
    private static volatile Scoreboard instance;
    private Map<String, Integer> playerScores;

    private BitmapFont font;
    private SpriteBatch batch;

    private Scoreboard() {
        playerScores = new HashMap<>();
        font = new BitmapFont(); // Default font
        batch = new SpriteBatch(); // Used for drawing
    }

    public static Scoreboard getInstance() {
        Scoreboard result = instance;
        if (result == null) {
            synchronized (Scoreboard.class) {
                result = instance;
                if (result == null) {
                    result = instance = new Scoreboard();
                }
            }
        }
        return instance;
    }

    public void updateScore(String playerName, int score) {
        playerScores.put(playerName, playerScores.getOrDefault(playerName, 0) + score);
    }
    public void updateScores(Map<String, Integer> newScores) {
        this.playerScores = newScores != null ? newScores : new HashMap<>(); // Fallback to empty map if null
    }


    public int getScore(String playerName) {
        return playerScores.getOrDefault(playerName, 0);
    }

    public Map<String, Integer> getPlayerScores() {
        return playerScores;
    }

    // nenaudojamas
    public void render() {
        if (playerScores == null) return; // Safety check, but ideally it should never be null here

        batch.begin();
        font.draw(batch, "-----Scoreboard-----", 850, 980);
        int yOffset = 960;


        for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
            font.draw(batch, entry.getKey() + ": " + entry.getValue(), 850, yOffset);
            yOffset -= 20;
        }

        batch.end();
    }

    // Call this method when disposing of resources, e.g., at the end of the game
    public void dispose() {
        font.dispose();
        batch.dispose();
    }

    public void removePlayer(int playerId) {
        String playerName = "Player_" + playerId;
        playerScores.remove(playerName);
        render();
    }
}
