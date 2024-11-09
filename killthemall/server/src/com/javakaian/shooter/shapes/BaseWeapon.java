package com.javakaian.shooter.shapes;

public abstract class BaseWeapon {
    protected IBullet bullet;

    protected BaseWeapon(IBullet bullet){
        this.bullet = bullet;
    }

    abstract public IBullet getBullet(float x, float y, float size, float angle, int id);
}
