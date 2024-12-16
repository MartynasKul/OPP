package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/// cia factory galima prideti
/// Taip pat ir abstract factory galima pritaikyti prie enemy kurimo.
///
///
/// Strategy dp galima pritaikyti enemy. galima prideti judejima, saudya, roaminima.
public class Enemy implements ClientGameEntity{

	private float x;
	private float y;
	private float size;
	private boolean visible = true;

	public Enemy(float x, float y, float size) {
		this.x = x;
		this.y = y;
		this.size = size;

	}
	@Override
	public void render(ShapeRenderer sr) {

		sr.setColor(Color.CYAN);
		sr.circle(x, y, size);
		sr.setColor(Color.WHITE);
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

}
