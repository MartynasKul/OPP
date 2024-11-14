package com.javakaian.shooter.Strategy;
import com.badlogic.gdx.graphics.Color;
public class GreenYellowRedStrategy implements ColorStrategy {
    @Override
    public String applyColorString(int health){
        if (health > 70) {return "Green";}
        else if (health > 30) { return "Yellow";}
        else { return "Red";}
    }

    @Override
    public Color applyColor(int health) {
        if(health>100){
            return Color.PURPLE;
        }
        else if (health > 70 && health <= 100) {
            return Color.GREEN;
        } else if (health > 30 && health <= 70) {
            return Color.YELLOW;
        } else {
            return Color.RED;
        }
    }
}