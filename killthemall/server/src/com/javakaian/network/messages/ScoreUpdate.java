package com.javakaian.network.messages;

public class ScoreUpdate {
    public int playerId;
    public int newScore;

    public ScoreUpdate() {} // No-arg constructor needed for KryoNet

    public ScoreUpdate(int playerId, int newScore) {
        this.playerId = playerId;
        this.newScore = newScore;
    }
}
