package com.javakaian.shooter.Strategy;
import com.badlogic.gdx.graphics.Color;

/// Galima pakeisti strategy PlayState failo Login metode.
/// new GradienStrategy()
/// new BlinkingRedStrategy()
/// new GreenYellowRedStrategy()
/// new OpacityStrategy()
/// new ThresholdShadeStrategy()
/// new FadeToBlackStrategy()
public interface ColorStrategy {
    Color applyColor(int Health);
    String applyColorString(int health); // grazina spalva pagal hp
}
