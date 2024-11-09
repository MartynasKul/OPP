package com.javakaian.shooter.shapes;

public class Pistol extends BaseWeapon{

    public Pistol(IBullet bullet){
        super(bullet);
    }
    
    @Override
    public IBullet getBullet(float x, float y, float size, float angle, int id) {
        bullet.setParameters(x, y, size, angle, id);
        return bullet;
    }
    
}
