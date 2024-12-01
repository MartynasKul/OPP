package com.javakaian.shooter.shapes.Iterator;
import com.javakaian.shooter.shapes.BaseEnemy;
import com.javakaian.shooter.shapes.Bullet;
import com.javakaian.shooter.shapes.Player;

import java.util.*;

public class GameEntityCollection {
    private List<Player> players = new ArrayList<>();
    private Queue<BaseEnemy> enemies = new LinkedList<>();
    private Set<Bullet> bullets = new HashSet<>();


    public void addPlayer(Player player) {
        players.add(player);
    }
    public void addEnemy(BaseEnemy enemy){
        enemies.add(enemy);
    }
    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    public GameIterator<Player> getPlayerIterator(){
        return new PlayerIterator(players);
    }
    public GameIterator<Bullet> getBulletIterator(){
        return new BulletIterator(bullets);
    }
    public GameIterator<BaseEnemy> getEnemyIterator(){
        return new EnemyIterator(enemies);
    }
}
