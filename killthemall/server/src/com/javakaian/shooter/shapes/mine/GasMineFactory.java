package com.javakaian.shooter.shapes.mine;

public class GasMineFactory extends AbstractMineFactory {

    @Override
    public IMine getMine(String shape, float x, float y) {
        switch (shape.toLowerCase()) {
            case "circle":
                return new GasCircleMine(x, y);
            case "rectangle":
                return new GasRectangleMine(x, y);
            case "square":
                return new GasSquareMine(x, y);
            default:
                throw new IllegalArgumentException("Unknown mine shape: " + shape);
        }
    }
}
