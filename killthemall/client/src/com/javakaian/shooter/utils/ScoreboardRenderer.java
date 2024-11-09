package com.javakaian.shooter.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.javakaian.shooter.shapes.Scoreboard;

import java.util.HashMap;
import java.util.Map;

//package com.javakaian.shooter.utils;

public class ScoreboardRenderer {
    private BitmapFont font;
    private SpriteBatch batch;

    private Map<String, Integer> playerScores;

    public ScoreboardRenderer() {
        font = new BitmapFont(); // Font initialization
        batch = new SpriteBatch(); // Batch initialization
        playerScores = new HashMap<>();
    }

    // Update the scoreboard data from the server
    public void updateScores(Map<String, Integer> newScores) {
        this.playerScores = newScores != null ? newScores : new HashMap<>(); // Fallback to empty map if null
    }

    public void updateFromScoreboard() {
        updateScores(Scoreboard.getInstance().getPlayerScores()) ;

        //Scoreboard.getInstance().render();
        render();
    }

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

    public void dispose() {
        font.dispose();
        batch.dispose();
    }

    //public void onScoreboardUpdateReceived(Map<String, Integer> updatedScores) {
    //    scoreboardRenderer.updateScores(updatedScores); // Update renderer with new data
    //}
}
