package com.javakaian.shooter.shapes;

public class PiercingDecorator extends BulletDecorator {

    private boolean piercing;

    public PiercingDecorator(IBullet wrappedBullet, boolean piercing) {
        super(wrappedBullet);
        this.piercing = piercing;
    }

    @Override
    public boolean getPiercing() {
        return  this.piercing;
    }

    @Override
    public IBullet clone() {
        return new PiercingDecorator(wrappedBullet.clone(), piercing);
    }
}
