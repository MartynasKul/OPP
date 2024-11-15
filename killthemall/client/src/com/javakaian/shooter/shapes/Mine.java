package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Mine {

    private float x;
    private float y;
    private float size;
    private boolean visible = true;

    public Mine(float x, float y, float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void render(ShapeRenderer sr) {
        if (!visible) return;

        sr.setColor(Color.RED); // Set mine color to red
        sr.circle(x, y, size);
        sr.setColor(Color.WHITE); // Reset to default color
    }

    // Getters and setters for position, visibility, and size
    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    public float getSize() { return size; }
    public void setSize(float size) { this.size = size; }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }

    public void activate() {
        // Define behavior when mine is activated, e.g., explode or disappear
        System.out.println("Mine activated!");
        visible = false; // Example: Hide the mine after activation
    }
}
