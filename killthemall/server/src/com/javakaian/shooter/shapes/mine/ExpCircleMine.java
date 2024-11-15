package com.javakaian.shooter.shapes.mine;

import com.badlogic.gdx.math.Rectangle;

public class ExpCircleMine implements IMine {
    private float x, y;
    private boolean visible;
    private String shapeType = "ExpCircle";
    private Rectangle bounds;

    public ExpCircleMine(float x, float y) {
        this.x = x;
        this.y = y;
        this.visible = true;
        this.bounds = new Rectangle(x, y, 20, 20); // Adjust size as needed
    }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    public String getShapeType() { return shapeType; }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }

    public Rectangle getBounds() { return bounds; }

    public void update(float deltaTime) {
        // Update logic if needed
    }

    public void activate() {
        System.out.println("Explosive Circle Mine activated!");
        // Add explosion effect
        this.visible = false;
    }
}
