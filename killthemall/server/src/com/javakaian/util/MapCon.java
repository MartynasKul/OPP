package com.javakaian.util;

import com.javakaian.shooter.shapes.BaseEnemy;
import com.javakaian.shooter.shapes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapCon {
    private final int mapColor;
    private final BaseEnemy enemy;

    public MapCon(int mapColor, BaseEnemy enemy) {
        this.mapColor = mapColor;
        this.enemy = enemy;
    }

    public int getMapColor() { return mapColor; }
    public BaseEnemy getEnemy() { return enemy; }
}
