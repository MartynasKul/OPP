package com.javakaian.shooter.Strategy;

import com.badlogic.gdx.graphics.Color;

//GOOD WORK
public class ThresholdShadeStrategy implements ColorStrategy {
    @Override
    public String applyColorString(int health){
        return "RED";
    }
    @Override
    public Color applyColor(int health) {
        if (health > 70) {
            return new Color(1, 0.6f, 0.6f, 1); // Light red
        } else if (health > 40) {
            return new Color(1, 0.3f, 0.3f, 1); // Medium red
        } else {
            return Color.RED; // Deep red for critical health
        }
    }
}
