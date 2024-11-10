package com.javakaian.shooter.utils;

public class EnemyData {
   // public int id;
    public String Type;
    public float x, y;
    public int health;

    public EnemyData(){}
    public EnemyData( String Type, float x, float y, int health) {
        //this.id = id;
        this.Type = Type;
        this.x = x;
        this.y = y;
        this.health = health;
    }
}
