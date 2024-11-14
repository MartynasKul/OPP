package com.javakaian.shooter.Strategy;
import com.badlogic.gdx.graphics.Color;
public class GradientStrategy implements ColorStrategy {
    @Override
    public String applyColorString(int health) {
        int red = (int) (255 * (1 - (health / 100.0)));
        int green = (int) (255 * (health / 100.0));
        return String.format("#%02X%02X00", red, green); // Returns a hex color code
    }

    @Override
    public Color applyColor(int health) {
        float greenValue = health / 100.0f;
        float redValue = 1.0f - greenValue;
        return new Color(redValue, greenValue, 0, 1); // Creates a gradient from green to red
    }
}