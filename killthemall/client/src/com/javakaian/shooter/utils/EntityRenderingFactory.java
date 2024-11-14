// EntityRenderingFactory.java
package com.javakaian.shooter.utils;

import com.javakaian.shooter.shapes.BaseEnemy;
import com.javakaian.shooter.shapes.Bullet;
import com.badlogic.gdx.graphics.Color;

public interface EntityRenderingFactory {
    Color getBulletColor(Bullet bullet);
    Color getEnemyColor(BaseEnemy enemy);
}
