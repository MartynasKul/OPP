package com.javakaian.shooter.shapes;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javakaian.network.messages.ShootMessage;

public class Player {

	private float size;
	private Vector2 position;
	private int id;
	private Rectangle boundRect;
	private boolean alive;
	private int health;
	private BaseWeapon weapon;

	public Player(float x, float y, float size, int id, BaseWeapon weapon) {
		this.position = new Vector2(x, y);
		this.size = size;
		this.id = id;
		this.boundRect = new Rectangle(x, y, this.size, this.size);
		this.alive = true;
		this.health = 100;
		this.weapon = weapon;
	}

	public IBullet shoot(ShootMessage pp) {
		IBullet bullet = weapon.bullet.clone();
		
		bullet.setParameters(getPosition().x + getBoundRect().width / 2,
		getPosition().y + getBoundRect().height / 2, 10, pp.getAngleDeg(), pp.getId());

		return bullet;
	}

	public void update(float deltaTime) {

		this.boundRect.x = position.x;
		this.boundRect.y = position.y;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rectangle getBoundRect() {
		return boundRect;
	}

	public boolean isAlive() {
		return alive;
	}

	public int getHealth() {
		return this.health;
	}

	public void increaseHealth() {
		if (this.health == 100)
			return;
		this.health += 10;
	}

	public void hit() {
		this.health -= weapon.bullet.getDamage();
		if (this.health <= 0) {
			this.alive = false;
		}
	}

}
