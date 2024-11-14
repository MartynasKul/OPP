package com.javakaian.shooter.Strategy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;

//BAD doesnt flash

public class FlashingRedStrategy implements ColorStrategy {
    private float transition = 0;
    private boolean increasing = true;

    @Override
    public String applyColorString(int health) {
        return "RED";
    }

    @Override
    public Color applyColor(int health) {
        // Adjust transition factor between 0 and 1
        if (increasing) {
            transition += Gdx.graphics.getDeltaTime();
            if (transition >= 1f) {
                transition = 1f;
                increasing = false;
            }
        } else {
            transition -= Gdx.graphics.getDeltaTime();
            if (transition <= 0f) {
                transition = 0f;
                increasing = true;
            }
        }

        // Create base colors
        Color startColor = new Color(1, 0, 0, 1);      // Red
        Color endColor = new Color(1, 0.5f, 0, 1);     // Orange

        // Create a new color instance to avoid mutating static colors
        Color currentColor = new Color(startColor);

        // Lerp towards endColor based on transition
        currentColor.lerp(endColor, transition);

        return currentColor;
    }
}
