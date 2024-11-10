package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class BaseEnemy implements Cloneable{
    protected float x;
    protected float y;
    protected int health;

    protected boolean visible = true;
    protected String shape;
    protected Rectangle boundRect;

    public BaseEnemy(){}
    public BaseEnemy(float x, float y, float size) {
        this.x = x;
        this.y = y;
        //this.boundRect = new Rectangle(x, y, size, size);
    }
    public BaseEnemy(float x, float y,  String shape, int health, int sizeX, int sizeY) {
        this.x = x;
        this.y = y;
        this.shape = shape;
        this.health = health;
        this.boundRect = new Rectangle(x, y, sizeX, sizeY);
    }

    public Rectangle getBoundRect() {
        return boundRect;
    }

    public void update(float deltaTime) {

        this.boundRect.x = x;
        this.boundRect.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getShape() {
        return shape;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    //public Rectangle getBoundRect() {
    //	return boundRect;
    //}

    @Override
    public BaseEnemy clone() {
        try {
            return (BaseEnemy) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

   public void render(ShapeRenderer renderer) {

   }

    public abstract void takeDamage(int damage);
    public abstract void applyEffect(Player player);
}
