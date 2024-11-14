package com.javakaian.util;

import com.javakaian.shooter.shapes.BaseEnemy;
import com.javakaian.shooter.shapes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DesertMapBuilder implements MapBuilder {
    private int mapColor;
    private BaseEnemy enemy;

    @Override
    public MapBuilder setMapColor() {
        this.mapColor = 1; 
        return this;
    }

    @Override
    public MapBuilder setEnemyType(float x, float y) {
        this.enemy = EnemyFactory.createRandomEnemy("Circle",x,y);
        return this;
    }
    @Override
    public MapCon build() {
        return new MapCon(mapColor, enemy);
    }
}