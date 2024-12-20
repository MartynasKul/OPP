package com.javakaian.shooter.shapes;

public class MachineGun extends BaseWeapon{

    public MachineGun(IBullet bullet){
        super(bullet);
    }
    
    @Override
    public IBullet getBullet(float x, float y, float size, float angle, int id) {
        bullet.setParameters(x, y, size, angle, id);
        return bullet;
    }

    @Override
    public void bulletShot(){};
}
