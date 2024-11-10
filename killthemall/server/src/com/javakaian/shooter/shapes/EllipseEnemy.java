package com.javakaian.shooter.shapes;

import com.badlogic.gdx.math.Ellipse;

public class EllipseEnemy extends BaseEnemy{

    protected Ellipse ellipse;

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
}
