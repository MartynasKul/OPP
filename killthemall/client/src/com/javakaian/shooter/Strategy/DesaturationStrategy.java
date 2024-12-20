package com.javakaian.shooter.Strategy;
import com.badlogic.gdx.graphics.Color;

public class DesaturationStrategy implements ColorStrategy {
    @Override
    public String applyColorString(int health) {
        return "RED";
    }

    @Override
    public Color applyColor(int health) {
        float saturation = health / 100.0f;
        return new Color(1 * saturation, 0 * saturation, 0 * saturation + (1 - saturation), 1);
    }
}