package com.javakaian.shooter.shapes.Iterator;

import com.javakaian.shooter.shapes.Player;

import java.util.List;

public class PlayerIterator implements GameIterator<Player>{
    private List<Player> players;
    private int index = 0;

    public PlayerIterator(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean hasNext() {
        return index < players.size();
    }

    @Override
    public Player next() {
        return players.get(index++);
    }

}
