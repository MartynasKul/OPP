package com.javakaian.shooter.shapes.Iterator;

import com.badlogic.gdx.utils.compression.lzma.Base;
import com.javakaian.shooter.shapes.BaseEnemy;

import java.util.Queue;

public class EnemyIterator implements GameIterator<BaseEnemy> {
    private Queue<BaseEnemy> enemies;
    private BaseEnemy[] enemyArray;
    private int index =0;

    public EnemyIterator(Queue<BaseEnemy> enemies){
        this.enemies = enemies;
        this.enemyArray = enemies.toArray(new BaseEnemy[0]);
    }

    @Override
    public boolean hasNext() {
        return index < enemies.size();
    }
    @Override
    public BaseEnemy next(){
        return enemyArray[index++];
    }
}
