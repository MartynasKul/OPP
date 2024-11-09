package com.javakaian.shooter.shapes;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface IBullet {
    abstract public int getDamage();
    abstract public void setParameters(float x, float y, float size, float angle, int id);
    abstract public void update(float deltaTime);
    abstract public Vector2 getPosition();
	abstract public void setPosition(Vector2 position) ;
	abstract public float getSize();
	abstract public void setSize(float size);
	abstract public boolean isVisible();
	abstract public void setVisible(boolean visible);
	abstract public int getId();
	abstract public Rectangle getBoundRect();
    abstract public IBullet clone();
} 
