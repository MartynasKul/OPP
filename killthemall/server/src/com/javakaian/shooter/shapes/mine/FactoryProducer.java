package com.javakaian.shooter.shapes.mine;

public class FactoryProducer {
    public static AbstractMineFactory getFactory(String type) {
        if ("gas".equalsIgnoreCase(type)) {
            return new GasMineFactory();
        } else if ("exp".equalsIgnoreCase(type)) {
            return new ExpMineFactory();
        }
        throw new IllegalArgumentException("Unknown factory type: " + type);
    }
}
