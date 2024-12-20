package com.javakaian.shooter.shapes;

public class WeaponSecurityProxy extends BaseWeapon {

    private final BaseWeapon realWeapon;
    private final Player player;

    public WeaponSecurityProxy(BaseWeapon realWeapon, Player player) {
        super(realWeapon.bullet);
        this.realWeapon = realWeapon;
        System.out.println("WeaponProxy.");
        this.player = player;
    }

    @Override
    public void bulletShot(){
        System.out.println("Bullet shot by "+ player.getName());
        realWeapon.bulletShot();
    }

    @Override
    public IBullet getBullet(float x, float y, float size, float angle, int id) {

        if (!player.isAlive()) {
            System.out.println("Security Proxy: Player is dead. Cannot shoot.");
            return null;
        }


        return realWeapon.getBullet(x, y, size, angle, id);
    }
}
