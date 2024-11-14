package com.javakaian.shooter.utils;

import com.javakaian.shooter.shapes.BaseEnemy;
import com.javakaian.shooter.shapes.Bullet;
import com.badlogic.gdx.graphics.Color;

public class AdvancedRenderingFactory implements EntityRenderingFactory {

    @Override
    public Color getBulletColor(Bullet bullet) {
        return Color.GOLD; // Advanced style for player
    }

    @Override
    public Color getEnemyColor(BaseEnemy enemy) {
        return Color.MAROON; // Advanced style for enemy
    }
}
