package com.javakaian.shooter.shapes;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class BulletDecorator implements IBullet {

    protected IBullet wrappedBullet;

    public BulletDecorator(IBullet wrappedBullet) {
        this.wrappedBullet = wrappedBullet;
    }

    @Override
    public int getDamage() {
        return wrappedBullet.getDamage();
    }

    @Override
    public void setParameters(float x, float y, float size, float angle, int id) {
        wrappedBullet.setParameters(x, y, size, angle, id);
    }
<<<<<<< HEAD

=======
>>>>>>> Simas
    @Override
    public void update(float deltaTime) {
        wrappedBullet.update(deltaTime);
    }

    @Override
    public Vector2 getPosition() {
        return wrappedBullet.getPosition();
    }

    @Override
    public void setPosition(Vector2 position) {
        wrappedBullet.setPosition(position);
    }

    @Override
    public float getSize() {
        return wrappedBullet.getSize();
    }

    @Override
    public void setSize(float size) {
        wrappedBullet.setSize(size);
    }

    @Override
    public boolean isVisible() {
        return wrappedBullet.isVisible();
    }

    @Override
    public void setVisible(boolean visible) {
        wrappedBullet.setVisible(visible);
    }

    @Override
    public void setPiercing(boolean piercing){wrappedBullet.setPiercing(piercing);}

    @Override
    public boolean getPiercing(){return wrappedBullet.getPiercing();}

    @Override
    public int getId() {
        return wrappedBullet.getId();
    }

    @Override
    public Rectangle getBoundRect() {
        return wrappedBullet.getBoundRect();
    }
    @Override
    public float getAngle() {
        return wrappedBullet.getAngle();
    }
<<<<<<< HEAD

=======
>>>>>>> Simas
    @Override
    public abstract IBullet clone();
}
