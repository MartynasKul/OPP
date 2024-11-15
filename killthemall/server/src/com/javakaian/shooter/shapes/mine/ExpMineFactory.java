package com.javakaian.shooter.shapes.mine;

public class ExpMineFactory extends AbstractMineFactory {

    @Override
    public IMine getMine(String shape, float x, float y) {
        switch (shape.toLowerCase()) {
            case "circle":
                return new ExpCircleMine(x, y);
            case "rectangle":
                return new ExpRectangleMine(x, y);
            case "square":
                return new ExpSquareMine(x, y);
            default:
                throw new IllegalArgumentException("Unknown mine shape: " + shape);
        }
    }
}
