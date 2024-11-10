package com.javakaian.util;

public class EnemyData {
    //public int id;
    public String enemyShape;
    float x, y;
    public int health;

    public EnemyData(){}
    public EnemyData(String shape, float x, float y, int health) {
        //this.id = id;
        this.enemyShape = shape;
        this.x = x;
        this.y = y;
        this.health = health;
    }
}
