package com.javakaian.shooter.shapes.mine;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface IMine {

    float getX();
    void setX(float x);
    float getY();
    void setY(float y);
	String getShapeType();
    boolean isVisible();
    void setVisible(boolean visible);
    Rectangle getBounds();
    void update(float deltaTime);
    void activate();
}