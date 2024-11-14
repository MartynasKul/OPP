package com.javakaian.util;

import com.javakaian.shooter.shapes.BaseEnemy;
import com.javakaian.shooter.shapes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface MapBuilder {
    MapBuilder setMapColor();
    MapBuilder setEnemyType(float x, float y);;
    MapCon build();
}