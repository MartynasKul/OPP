package com.javakaian.shooter.shapes;

import java.util.HashMap;
import java.util.Map;

public class ParticleFactory {
    private static Map<String, Flyweight> particleCache = new HashMap<>();

    public static Flyweight getParticle(String shapeType) {
        String key = shapeType;
        
        Flyweight particle = particleCache.get(key);
        if (particle == null) {
            particle = new Particle(shapeType);
            particleCache.put(key, particle);
        }
        return particle;
    }
}
