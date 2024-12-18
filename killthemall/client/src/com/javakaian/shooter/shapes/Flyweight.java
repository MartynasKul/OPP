package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Flyweight {
    void render(ShapeRenderer sr, float x, float y, float alpha);
}
