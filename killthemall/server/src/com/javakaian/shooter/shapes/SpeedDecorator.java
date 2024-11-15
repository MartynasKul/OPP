package com.javakaian.shooter.shapes;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
public class SpeedDecorator extends BulletDecorator {

    private float speedMultiplier;

    public SpeedDecorator(IBullet wrappedBullet, float speedMultiplier) {
        super(wrappedBullet);
        this.speedMultiplier = speedMultiplier;
    }

    @Override
    public void update(float deltaTime) {

        super.update(deltaTime);
        Vector2 position = wrappedBullet.getPosition();

        float angle = wrappedBullet.getAngle();
        double originalSpeed = deltaTime * 800;  // Base speed (can be adjusted)
        double adjustedSpeed = originalSpeed * speedMultiplier;

        position.x += Math.cos(angle) * adjustedSpeed;
        position.y -= Math.sin(angle) * adjustedSpeed;

        wrappedBullet.setPosition(position);
    }
    @Override
    public IBullet clone() {
        return new SpeedDecorator(wrappedBullet.clone(), speedMultiplier);
    }
}
