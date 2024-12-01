package com.javakaian.shooter.shapes.Iterator;

import com.javakaian.shooter.shapes.Bullet;
import java.util.Set;

public class BulletIterator implements GameIterator<Bullet>{
    private Bullet[] bullets;
    private int index =0;

    public BulletIterator(Set<Bullet> bullets) {
        this.bullets = bullets.toArray(new Bullet[0]);
    }

    @Override
    public boolean hasNext() {
        return index < bullets.length;
    }

    @Override
    public Bullet next() {
        return bullets[index++];
    }
}
