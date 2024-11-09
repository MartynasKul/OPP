package com.javakaian.shooter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javakaian.network.messages.*;
import org.apache.log4j.Logger;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.javakaian.network.OServer;

import com.javakaian.network.messages.GameWorldMessage;
import com.javakaian.network.messages.LoginMessage;
import com.javakaian.network.messages.LogoutMessage;
import com.javakaian.network.messages.PlayerDied;
import com.javakaian.network.messages.PositionMessage;
import com.javakaian.network.messages.ShootMessage;

import com.javakaian.shooter.shapes.Bullet;

import com.javakaian.shooter.shapes.Enemy;
import com.javakaian.shooter.shapes.HighDamageBullet;
import com.javakaian.shooter.shapes.IBullet;
import com.javakaian.shooter.shapes.Pistol;
import com.javakaian.shooter.shapes.BaseEnemy;
import com.javakaian.shooter.shapes.BaseWeapon;
import com.javakaian.shooter.shapes.Player;
import com.javakaian.shooter.shapes.RegularBullet;
import com.javakaian.util.MessageCreator;
import org.lwjgl.Sys;

public class ServerWorld implements OMessageListener {

	private List<Player> players;
	private List<BaseEnemy> enemies;
	private List<IBullet> bullets;

	private OServer oServer;

	private float deltaTime = 0;

	private float enemyTime = 0f;

	private LoginController loginController;

	private Logger logger = Logger.getLogger(ServerWorld.class);

	public ServerWorld() {

		oServer = new OServer(this);
		players = new ArrayList<>();
		enemies = new ArrayList<>();
		bullets = new ArrayList<>();

		loginController = new LoginController();

	}

	public void update(float deltaTime) {

		this.deltaTime = deltaTime;
		this.enemyTime += deltaTime;

		oServer.parseMessage();


		broadcastScoreBoardUpdate();
		//System.console().printf("Updated score");

		// update every object
		players.forEach(p -> p.update(deltaTime));
		enemies.forEach(e -> e.update(deltaTime));
		bullets.forEach(b -> b.update(deltaTime));
		players.forEach(p -> p.setName());

		checkCollision();

		// update object list. Remove necessary
		players.removeIf(p -> !p.isAlive());
		enemies.removeIf(e -> !e.isVisible());
		bullets.removeIf(b -> !b.isVisible());

		spawnRandomEnemy();

		GameWorldMessage m = MessageCreator.generateGWMMessage(enemies, bullets, players);
		oServer.sendToAllUDP(m);

	}

	/**
	 * Spawns an enemy to the random location. In 0.4 second if enemy list size is
	 * lessthan 15.
	 */
	private void spawnRandomEnemy() {

		if (enemyTime >= 0.4 && enemies.size() <= 15) {
			enemyTime = 0;
			if (enemies.size() % 5 == 0)
				logger.debug("Number of enemies : " + enemies.size());
			BaseEnemy e = new Enemy(new SecureRandom().nextInt(1000), new SecureRandom().nextInt(1000), 10);
			
			
			//can implement factory here.
			BaseEnemy b = e.clone();
			
			enemies.add(b);
		}
	}

	private void broadcastScoreBoardUpdate(){
		Map<String, Integer> currentScores = Scoreboard.getInstance().getScores();

		// Send the updated scores to all connected clients
		oServer.sendToAllUDP(currentScores);
	}

	private void checkCollision() {

		for (IBullet b : bullets) {

			for (BaseEnemy e : enemies) {

				if (b.isVisible() && e.getBoundRect().overlaps(b.getBoundRect())) {
					b.setVisible(false);
					e.setVisible(false);
					players.stream()
							.filter(p -> p.getId() == b.getId())
							.findFirst()
							.ifPresent(player -> {
								player.increaseHealth();
								player.increaseScore(5); // Increase score for hitting an enemy
								Scoreboard.getInstance().updateScore(player.getName(), player.getScore());
								ScoreUpdate scoreUpdate = new ScoreUpdate(player.getId(), player.getScore());
								oServer.sendToAllUDP(scoreUpdate);
							});

				}
			}
			for (Player p : players) {
				if (b.isVisible() && p.getBoundRect().overlaps(b.getBoundRect()) && p.getId() != b.getId()) {
					b.setVisible(false);
					p.hit();
					if (!p.isAlive()) {
						// Notify all clients that the player died
						PlayerDied m = new PlayerDied();
						m.setId(p.getId());
						oServer.sendToAllUDP(m);

						// Find the player who shot the bullet and increase their score
						players.stream()
								.filter(player -> player.getId() == b.getId())
								.findFirst()
								.ifPresent(player ->{
									player.increaseScore(20);
									player.increaseHealth();
									Scoreboard.getInstance().updateScore(player.getName(), player.getScore());
									ScoreUpdate scoreUpdate = new ScoreUpdate(player.getId(), player.getScore());
									oServer.sendToAllUDP(scoreUpdate);

								}); // Award score for killing another player

					}

				}
			}

		}

	}

	@Override
	public void loginReceived(Connection con, LoginMessage m) {

		int id = loginController.getUserID();

		BaseWeapon weapon = new Pistol(new HighDamageBullet());

		//players.add(new Player(m.getX(), m.getY(), 50, id, weapon));
		//logger.debug("Login Message recieved from : " + id);

		Player newPlayer = new Player(m.getX(), m.getY(), 50, id, "Player_"+id, weapon);
		players.add(newPlayer);
		logger.debug("Login Message received from : "+ newPlayer.getName());
		Scoreboard.getInstance().addPlayer(newPlayer.getName());
		Scoreboard.getInstance().updateScore(newPlayer.getName(), newPlayer.getScore());


		m.setId(id);
		oServer.sendToUDP(con.getID(), m);
	}

	@Override
	public void logoutReceived(LogoutMessage m) {

		players.stream().filter(p -> p.getId() == m.getId()).findFirst().ifPresent(p -> {
			players.remove(p);
			loginController.putUserIDBack(p.getId());
			Scoreboard.getInstance().removePlayer(p.getId());
			broadcastScoreBoardUpdate();
		});
		logger.debug("Logout Message recieved from : " + m.getId() + " Size: " + players.size());

	}

	@Override
	public void playerMovedReceived(PositionMessage move) {

		players.stream().filter(p -> p.getId() == move.getId()).findFirst().ifPresent(p -> {

			Vector2 v = p.getPosition();
			switch (move.getDirection()) {
			case LEFT:
				v.x -= deltaTime * 200;
				break;
			case RIGHT:
				v.x += deltaTime * 200;
				break;
			case UP:
				v.y -= deltaTime * 200;
				break;
			case DOWN:
				v.y += deltaTime * 200;
				break;
			default:
				break;
			}

		});

	}

	@Override
	public void shootMessageReceived(ShootMessage pp) {

		players.stream().filter(p -> p.getId() == pp.getId()).findFirst()
				.ifPresent(p -> bullets.add(p.shoot(pp)));
	}

}
