package com.javakaian.shooter.shapes;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class HighDamageBullet implements IBullet{

    private Vector2 position;
	private float size;
	private float angle;
	private boolean visible = true;

	private float ttlCounter = 0;
	private int id;
	private Rectangle boundRect;

    @Override
    public int getDamage() {
        return 25;
    }

    @Override
    public void setParameters(float x, float y, float size, float angle, int id) {
        this.position = new Vector2(x, y);
        this.size = size;
        this.angle = angle;
        this.id = id;
        this.boundRect = new Rectangle(x, y, size, size);
    }

    public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getId() {
		return id;
	}

	public Rectangle getBoundRect() {

		return boundRect;
	}

    @Override
    public void update(float deltaTime) {
        float speed = deltaTime * 800;
		this.ttlCounter += deltaTime;

		float dx = (float) Math.cos(angle);
		float dy = (float) Math.sin(angle);

		position.y -= speed * dy;
		position.x += speed * dx;
		if (ttlCounter > 2) {
			visible = false;
			ttlCounter = 0;
		}

		this.boundRect.x = position.x;
		this.boundRect.y = position.y;
    }
    
    @Override
    public IBullet clone() {
        HighDamageBullet clone = new HighDamageBullet();
        return clone;
    }
}
