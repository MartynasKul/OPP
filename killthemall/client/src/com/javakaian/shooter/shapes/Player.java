package com.javakaian.shooter.shapes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.javakaian.shooter.Strategy.*;

import java.util.ArrayList;
import java.util.List;

public class Player implements ClientGameEntity{

	private float size;
	private Vector2 position;
	private int id = -1;
	private int health;
	private String name;
	private int score;

	private ColorStrategy colorStrategy;
	private Color currentColor;
	private List<ColorStrategy> strategies;
	private int currentStrategyIndex;
	private float blinkTimer = 0;
	private boolean isRedVisible = true;


	private Vector2 center;

	public Player(float x, float y, float size, String name, ColorStrategy colorStrategy) {
		this.position = new Vector2(x, y);
		this.size = size;
		this.health = 100;
		center = new Vector2(x, y);
		this.name = name;
		this.score=0;
		this.currentStrategyIndex=0;
		this.colorStrategy = colorStrategy;

		strategies = new ArrayList<>();
		strategies.add(new DesaturationStrategy());
		strategies.add(new GradientStrategy());
		strategies.add(new GreenYellowRedStrategy());
		strategies.add(new OpacityStrategy());
		strategies.add(new ThresholdShadeStrategy());
		strategies.add(new FadeToBlackStrategy());

	}

	// Method to set a different color strategy at runtime if desired
	public void setColorStrategy(ColorStrategy colorStrategy) {
		this.colorStrategy = colorStrategy;
	}

	public void update() {

		currentStrategyIndex++; // Cycle through strategies
		if(currentStrategyIndex >= strategies.size()) {
			currentStrategyIndex = 0;
		}
		//setColorStrategy(strategies.get(currentStrategyIndex));
		this.colorStrategy = strategies.get(currentStrategyIndex);
		System.out.println("Switched to strategy: " + colorStrategy.getClass().getSimpleName());
	}
	// Get the current color based on health using the assigned strategy
	public String getColorBasedOnHealthString() {
		return colorStrategy.applyColorString(health);
	}
	public Color getColorBasedOnHealth(){
		return colorStrategy.applyColor(health);
	}
	@Override
	public void render(ShapeRenderer sr) {

		Color currentColor = colorStrategy.applyColor(health);
		// Enable blending
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		sr.setColor(currentColor);
		sr.rect(position.x, position.y, size, size);
		sr.end();
		sr.begin(ShapeType.Filled);
		sr.rect(position.x, position.y, this.health / 2.0f, size);
		sr.end();
		sr.begin(ShapeType.Line);

		// Disable blending after drawing
		Gdx.gl.glDisable(GL20.GL_BLEND);

		center.x = position.x + size / 2;
		center.y = position.y + size / 2;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getCenter() {
		return center;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getScore(){
		return this.score;
	}
	public void setScore(int score){
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void increaseScore(int score) {
		this.score+=score;
	}

	public void increaseHealth(int health) {
		this.health+=health;
	}


}