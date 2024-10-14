package com.javakaian.shooter.shapes;
import java.util.HashMap;
import java.util.Map;

/// Singleton Klase Scoreboard
/// privatus konstruktorius, isorines klases negali sukurti naujo scoreboardo
/// statinis instance kuris bus vienintelis zaidime
/// globalus access pointas gauti scoreboardui
/// tasku pridejimo metodas.
public class Scoreboard {
    private static volatile Scoreboard instance;

    private Map<String, Integer> playerScores;
    private Scoreboard() {
        playerScores = new HashMap<>();
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

    public void updateScore(String playername, int score) {
        playerScores.put(playername, playerScores.getOrDefault(playername, 0) + score);
    }
    public int getscore(String playername) {
        return playerScores.getOrDefault(playername, 0);
    }

    ///Pakeisti kad butu rodoma ekrano kampe.
    public void displayScores(){
        System.out.println("+++ ScoreBaord +++");
        for(Map.Entry<String, Integer> entry : playerScores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
