package com.javakaian.network.messages;

import com.javakaian.shooter.shapes.BaseEnemy;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This message will be broadcast to all clients 60 times per second. It
 * contains position arrays of enemies,players and bullets.
 * 
 * @author oguz
 *
 */
public class GameWorldMessage {

	/** X,Y Coordinates of all the enemies.-> x1,y1,x2,y2,x3,y3.. */
	private List<BaseEnemy> enemies = new ArrayList<>();
	/**
	 * X,Y,ID,Health of all the players.->
	 * x1,y1,ID1,Health1,x2,y2,ID2,Health2,x3,y3,ID3,Health3..
	 */
	private float[] players;
	/** X,Y,Size of all the bullets.-> x1,y1,s1,x2,y2,s2,x3,y3,s3.. */
	private float[] bullets;
	private int mapColor;

	public List<BaseEnemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<BaseEnemy> enemies) {
        this.enemies.addAll(enemies);
	}

	public float[] getPlayers() {
		return players;
	}

	public void setPlayers(float[] players) {
		this.players = players;
	}

	public float[] getBullets() {
		return bullets;
	}

	public void setBullets(float[] bullets) {
		this.bullets = bullets;
	}
	public int getMapColor() {
        return mapColor;
    }

    public void setMapColor(int mapColor) {
        this.mapColor = mapColor;
    }

}
