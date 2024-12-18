package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class ParticleManager{

    private final Flyweight sharedParticle; 
    private final List<ParticleState> particles = new ArrayList<>();

    private static class ParticleState {
        private float x;
        private float y;
        private float lifetime; 
        private float alpha = 1.0f; 

        public ParticleState(float x, float y, float lifetime) {
            this.x = x;
            this.y = y;
            this.lifetime = lifetime;
        }
    }

    public ParticleManager(String type) {
        this.sharedParticle = ParticleFactory.getParticle(type);
    }

    public void spawnParticle(float x, float y, float lifetime) {
        particles.add(new ParticleState(x, y, lifetime));
    }

    public void update(float deltaTime) {
        for (int i = 0; i < particles.size(); i++) {
            ParticleState state = particles.get(i);

            state.x += state.x * deltaTime / 50;
            state.y += state.y * deltaTime / 50;

            state.lifetime -= deltaTime;
            state.alpha = Math.max(0, state.lifetime / 2);

            if (state.lifetime <= 0) {
                particles.remove(i);
                i--;
            }
        }
    }

    
    public void render(ShapeRenderer sr) {
        
        for (ParticleState state : particles) {
            sharedParticle.render(sr, state.x, state.y, state.alpha);
        }
    }
}
