package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    @Override
    public void render(ShapeRenderer sr) {
        sr.setColor(Color.CYAN);
        sr.rect(x, y, rectangle.width, rectangle.height);
        sr.setColor(Color.WHITE);
        sr.rect(x,y,rectangle.width+2,rectangle.height+2);


    }
}