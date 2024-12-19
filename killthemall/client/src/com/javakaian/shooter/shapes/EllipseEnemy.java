package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Ellipse;
import com.sun.org.apache.bcel.internal.generic.DCONST;

public final class EllipseEnemy extends BaseEnemy implements ClientGameEntity{
    private Ellipse ellipse;

    public EllipseEnemy(){}

    public EllipseEnemy(float x, float y){
        super(x,y,"Ellipse", 2, 30,30);
        this.ellipse = new Ellipse(x,y, 30,20);
    }
    @Override
    public void takeDamage(int damage) {
        this.health -= damage*2;
    }
    @Override
    public void applyEffect(Player player){
        //sitas enemy tsg padaro
    }
    
    @Override
    protected void move() {
        System.out.println("The Ellipse Enemy is Ellipsing towards YOU!!");
        setX(getX()+5);
        setY(getY()+5);

        //Pajuda diagonaliai i desine/aukstyn
    }
    @Override
    protected void attack() {
        System.out.println("The Ellipse enemy is attacking YOU");

    }

    @Override
    public void render(ShapeRenderer sr) {
        sr.setColor(Color.CYAN);
        sr.ellipse(x, y, ellipse.width, ellipse.height);
        sr.setColor(Color.WHITE);
        sr.rect(x,y, ellipse.height+2 ,ellipse.width+2);
        //System.out.printf("Ellipse enemy spawned at %a %b with %c health", x,y, health);
    }
}
