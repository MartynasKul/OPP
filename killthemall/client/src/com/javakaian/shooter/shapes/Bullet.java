package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;


/// cia galima prototype panaudoti kuriant naujus ammo type
/// base klase butu ammo
/// sekancios: bullet, rocket, bolt, laser, hook
public class Bullet implements ClientGameEntity{

	private Vector2 position;
	private float size;
	private boolean visible = true;
	public Bullet(float x, float y, float size) {
		this.position = new Vector2(x, y);
		this.size = size;
	}
	@Override
	public void render(ShapeRenderer sr) {
		sr.rect(position.x, position.y, size, size);
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

}
