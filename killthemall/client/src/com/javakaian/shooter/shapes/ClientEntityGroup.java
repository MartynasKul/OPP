package com.javakaian.shooter.shapes;

import java.util.ArrayList;

import java.util.List;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ClientEntityGroup implements ClientGameEntity {
    private List<ClientGameEntity> children = new ArrayList<>();

    public void add(ClientGameEntity entity) {
        children.add(entity);
    }

    public void remove(ClientGameEntity entity) {
        children.remove(entity);
    }

    public void clear() {
        children = new ArrayList<>();
    }

    @Override
    public void render(ShapeRenderer sr) {
        for (ClientGameEntity entity : children) {
            entity.render(sr);
        }
    }
}
