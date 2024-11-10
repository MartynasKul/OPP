package com.javakaian.shooter.shapes;

import com.badlogic.gdx.math.Rectangle;

public class SquareEnemy extends BaseEnemy{

    protected Rectangle rectangle;

    public SquareEnemy(){}
    public SquareEnemy(float x, float y) {
        super(x,y, "Square", 1, 20, 20);
        this.rectangle = new Rectangle(x,y,10,10);
    }
    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    @Override
    public void applyEffect(Player player) {
        player.increaseHealth(20);
    }
}
