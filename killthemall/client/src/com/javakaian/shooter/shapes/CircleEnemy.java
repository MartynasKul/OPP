package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public final class CircleEnemy extends BaseEnemy{
    private Circle circle;

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
    @Override
    public void render(ShapeRenderer sr) {
        sr.setColor(Color.CYAN);
        sr.circle(x, y, circle.radius);
        sr.setColor(Color.WHITE);
        sr.rect(x,y,circle.radius+2,circle.radius+2);
        //System.out.printf("Circle enemy spawned at %a %b with %c health", x,y, health);
    }
    @Override
    protected void move() {
        System.out.println("The Circle Enemy is Circling around YOU!!");
    }
    @Override
    protected void attack() {
        System.out.println("The Circle enemy is attacking YOU");
    }
    public void dispose(){
    }
}
