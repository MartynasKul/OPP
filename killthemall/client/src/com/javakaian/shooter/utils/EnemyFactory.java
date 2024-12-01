package com.javakaian.shooter.utils;

import com.javakaian.shooter.shapes.BaseEnemy;
import com.javakaian.shooter.shapes.CircleEnemy;
import com.javakaian.shooter.shapes.EllipseEnemy;
import com.javakaian.shooter.shapes.SquareEnemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyFactory {

    private static final List<String> ENEMY_TYPES = new ArrayList<>();

    static {
        ENEMY_TYPES.add("Ellipse");
        ENEMY_TYPES.add("Circle");
        ENEMY_TYPES.add("Square");
    }
    // Factory method to create an enemy based on type
    public static BaseEnemy createEnemy(String type, float x, float y) {
        switch (type) {
            case "Ellipse":
                return new EllipseEnemy(x, y);
            case "Circle":
                return new CircleEnemy(x, y);
            case "Square":
                return new SquareEnemy(x, y);
            default:
                throw new IllegalArgumentException("Unknown enemy UNGA type: " + type);
        }
    }

    public static BaseEnemy createRandomEnemy(float x, float y) {
        String randomType = ENEMY_TYPES.get(new Random().nextInt(ENEMY_TYPES.size()));
        return createEnemy(randomType, x, y);
    }


}
