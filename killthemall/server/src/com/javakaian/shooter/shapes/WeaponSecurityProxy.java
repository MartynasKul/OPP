package com.javakaian.shooter.shapes;

public class WeaponSecurityProxy extends BaseWeapon {

    private final BaseWeapon realWeapon; // The actual weapon
    private final Player player;        // Player associated with the weapon

    public WeaponSecurityProxy(BaseWeapon realWeapon, Player player) {
        super(realWeapon.bullet); // Pass the bullet to the proxy
        this.realWeapon = realWeapon;
        System.out.println("WeaponProxy.");
        this.player = player;
    }

    @Override
    public void bulletShot(){
        System.out.println("Bullet shot by "+ player.getName());
    }

    @Override
    public IBullet getBullet(float x, float y, float size, float angle, int id) {
        // Security rules before allowing the weapon to shoot
        if (!player.isAlive()) {
            System.out.println("Security Proxy: Player is dead. Cannot shoot.");
            return null; // Prevent shooting if player is dead
        }

        if (player.getScore() > 300) {
            //player.setName("tomas")
            System.out.println("Security Proxy: Pasiekta 300.");
            return null; // Prevent shooting if score is too low
        }

        // Forward the call to the real weapon if all checks pass
        return realWeapon.getBullet(x, y, size, angle, id);
    }
}
