package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Particle implements Flyweight{

    private final Color color = Color.RED;
    private final float size = 10;
    private final float velocity = 10;
    private final String shapeType;

    public Particle(String shapeType) {
        this.shapeType = shapeType;
    }

    @Override
    public void render(ShapeRenderer sr, float x, float y, float alpha) {
        
        sr.setColor(color); 

        if (shapeType == "square")
        {
            sr.rect(x-5, y+5, size, size);
            sr.rect(x-5, y-5, size, size);
            sr.rect(x+5, y-5, size, size);
            sr.rect(x+5, y+5, size, size);
        }
        else if (shapeType == "circle")
        {
            sr.circle(x-5, y+5, size);
            sr.circle(x-5, y-5, size);
            sr.circle(x+5, y-5, size);
            sr.circle(x+5, y+5, size);
        }
    }

    public float GetVelocity()
    {
        return velocity;
    }
}

