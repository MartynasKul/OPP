package com.javakaian.shooter.Strategy;

// GOOD WORK.
import com.badlogic.gdx.graphics.Color;

public class FadeToBlackStrategy implements ColorStrategy {

    @Override
    public String applyColorString(int health){
        if(health>50){
            return "RED";
        }
        else{
            return "BLACK";
        }
    }
    @Override
    public Color applyColor(int health) {
        float healthRatio = health / 100.0f;
        Color color = new Color(Color.RED); // Start with red color
        color.lerp(0, 0, 0, 1, 1 - healthRatio); // Lerp to black (0, 0, 0, 1)
        return color;
    }
}
