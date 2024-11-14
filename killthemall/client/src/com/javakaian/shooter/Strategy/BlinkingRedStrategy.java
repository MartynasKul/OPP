package com.javakaian.shooter.Strategy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

//Not Workign
public class BlinkingRedStrategy implements ColorStrategy {
    private boolean blink = false;
    private float timer = 0;
    private boolean isRed = true; // Initial color state

    @Override
    public String applyColorString(int health) {
        if (health < 20) {
            blink = !blink; // Toggle blink state
            return blink ? "Red" : "Transparent";
        }
        return "White"; // Use a default color for non-critical health
    }

    //@Override
    //public Color applyColor(int health) {
    //    if(health>100){
    //        blink = !blink;
    //        return blink ? Color.PURPLE : Color.GREEN;
    //    }
    //    if (health < 40) {
    //        blink = !blink; // Toggle blink state
    //        return blink ? Color.RED : new Color(1, 0, 0, 0); // Red or transparent
    //    }
    //    return Color.WHITE; // Default color when not critically low
    //}

    @Override
    public Color applyColor(int health) {
        if (health < 40) { // Only blink if health is critically low
            timer += Gdx.graphics.getDeltaTime();
            if (timer > 0.5f) { // Toggle every 0.5 seconds
                isRed = !isRed;
                timer = 0;
            }
            return isRed ? Color.RED : new Color(1, 0, 0, 0); // Alternate between red and transparent
        }
        return Color.WHITE; // Default color when health is above critical
    }
}
