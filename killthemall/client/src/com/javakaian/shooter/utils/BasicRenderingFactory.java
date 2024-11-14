
package com.javakaian.shooter.utils;

import com.javakaian.shooter.shapes.BaseEnemy;
import com.javakaian.shooter.shapes.Bullet;
import com.badlogic.gdx.graphics.Color;

public class BasicRenderingFactory implements EntityRenderingFactory {

    @Override
    public Color getBulletColor( Bullet bullet) {
        return Color.BLUE; // Basic style for player
    }

    @Override
    public Color getEnemyColor(BaseEnemy enemy) {
        return Color.RED; // Basic style for enemy
    }
}
