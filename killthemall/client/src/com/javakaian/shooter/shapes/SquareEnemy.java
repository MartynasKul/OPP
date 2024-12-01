package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public final class SquareEnemy extends BaseEnemy{

    private Rectangle rectangle;

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
       // System.out.printf("Square enemy spawned at %a %b with %c health", x,y, health);
    }

    @Override
    protected void move() {
        System.out.println("The Square Enemy is Squaring in on YOU!!");
    }

    @Override
    protected void attack() {
        System.out.println("The Square enemy is attacking YOU");
    }
}
