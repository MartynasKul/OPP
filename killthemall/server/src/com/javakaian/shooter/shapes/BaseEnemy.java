package com.javakaian.shooter.shapes;

import com.badlogic.gdx.math.Rectangle;

public abstract class BaseEnemy implements Cloneable {

	protected float x;
	protected float y;
	protected boolean visible = true;
	protected Rectangle boundRect;

	public BaseEnemy(float x, float y, float size) {
		this.x = x;
		this.y = y;
		this.boundRect = new Rectangle(x, y, size, size);
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

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Rectangle getBoundRect() {
		return boundRect;
	}

    @Override
    public BaseEnemy clone() {
        try {
            return (BaseEnemy) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
