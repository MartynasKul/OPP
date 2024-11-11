package com.javakaian.shooter.shapes;

public class DamageDecorator extends BulletDecorator {

    private int additionalDamage;

    public DamageDecorator(IBullet wrappedBullet, int additionalDamage) {
        super(wrappedBullet);
        this.additionalDamage = additionalDamage;
    }

    @Override
    public int getDamage() {
        return  additionalDamage; // Increase damage
    }

    @Override
    public IBullet clone() {
        return new DamageDecorator(wrappedBullet.clone(), additionalDamage);
    }
}
