package com.javakaian.shooter.shapes.mine;

public abstract class AbstractMineFactory {
    public abstract IMine getMine(String shape, float x, float y);
}
