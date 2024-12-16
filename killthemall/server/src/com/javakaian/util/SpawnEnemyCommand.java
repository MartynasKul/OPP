package com.javakaian.util;

import java.security.SecureRandom;
import java.util.List;

import com.badlogic.gdx.utils.compression.lzma.Base;
import com.javakaian.network.OServer;
import com.javakaian.shooter.shapes.BaseEnemy;
import com.javakaian.shooter.shapes.CircleEnemy;

class SpawnEnemyCommand implements Command<BaseEnemy> {
    private float size;
    private float[] location;

    public SpawnEnemyCommand(float[] location) {
        this.location = location;
    }

    @Override
    public void execute(OServer oServer, List<BaseEnemy> enemies) {
        System.out.println("Spawning enemy of at location: " + location[0] + " " + location[1] + "size: " + size);

        float x = location[0];
        float y = location[1];
		BaseEnemy enemy = new CircleEnemy(x, y);
		EnemyData enemyData = new EnemyData(
				enemy.getShape(),
				enemy.getX(),
				enemy.getY(),
				enemy.getHealth()
		);
		oServer.sendToAllUDP(enemyData);
		enemies.add(enemy);
    }
}

