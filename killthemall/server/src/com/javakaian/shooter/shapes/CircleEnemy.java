package com.javakaian.shooter.shapes;

import com.badlogic.gdx.math.Circle;

public class CircleEnemy extends BaseEnemy {
    protected Circle circle;


    public CircleEnemy(){}
    public CircleEnemy(float x, float y) {
        super(x,y,"Circle", 1, 20, 20);
        this.circle = new Circle(x,y,10);
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    @Override
    public void applyEffect(Player player) {
        player.increaseScore(player.getScore() + 2*health);

    }
}
