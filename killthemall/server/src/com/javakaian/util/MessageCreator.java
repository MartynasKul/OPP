package com.javakaian.util;

import java.util.List;

import com.javakaian.network.messages.GameWorldMessage;
import com.javakaian.shooter.shapes.BaseEnemy;
import com.javakaian.shooter.shapes.IBullet;
import com.javakaian.shooter.shapes.Player;

public class MessageCreator {

	private MessageCreator() {

	}

	/**
	 * Creates a GameWorldMessage. This message will be broadcasted to the all
	 * clients over UDP.
	 * 
	 * Every objects in server like Enemies,Players,Bullets will be converted to the
	 * float arrays and broadcasted.
	 */
	public static GameWorldMessage generateGWMMessage(List<BaseEnemy> enemies, List<IBullet> bullets, List<Player> players, int Mapcolor, float[] particleCoords) {

		GameWorldMessage gwm = new GameWorldMessage();
		//nebenaudojamas
		float[] coordinates = new float[enemies.size() * 2];

		for (int i = 0; i < enemies.size(); i++) {
			coordinates[i * 2] = enemies.get(i).getX();
			coordinates[i * 2 + 1] = enemies.get(i).getY();
		}

		gwm.setEnemies(enemies);

		float[] pcord = new float[players.size() * 4];
		for (int i = 0; i < players.size(); i++) {

			pcord[i * 4] = players.get(i).getPosition().x;
			pcord[i * 4 + 1] = players.get(i).getPosition().y;
			pcord[i * 4 + 2] = players.get(i).getId();
			pcord[i * 4 + 3] = players.get(i).getHealth();
		}

		gwm.setPlayers(pcord);

		float[] barray = new float[bullets.size() * 3];
		for (int i = 0; i < bullets.size(); i++) {
			barray[i * 3] = bullets.get(i).getPosition().x;
			barray[i * 3 + 1] = bullets.get(i).getPosition().y;
			barray[i * 3 + 2] = bullets.get(i).getSize();
		}
		gwm.setBullets(barray);
		gwm.setMapColor(Mapcolor);
		gwm.setCollisionCoords(particleCoords);

		return gwm;
	}

}
