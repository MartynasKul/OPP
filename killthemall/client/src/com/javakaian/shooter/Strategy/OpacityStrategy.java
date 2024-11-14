package com.javakaian.shooter.Strategy;
import com.badlogic.gdx.graphics.Color;
public class OpacityStrategy implements ColorStrategy{
    @Override
    public String applyColorString(int health) {
        int alpha = (int) (255 * (health / 100.0)); // Calculate alpha based on health
        return String.format("rgba(255, 0, 0, %d)", alpha); // Red with varying opacity
    }
    @Override
    public Color applyColor(int health) {
        float alpha = health / 100.0f; // Alpha decreases as health decreases
        return new Color(1, 0, 0, alpha); // Red with varying opacity
    }
}