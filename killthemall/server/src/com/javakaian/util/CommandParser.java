package com.javakaian.util;

public class CommandParser {
    public static Command parse(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length < 2 || !parts[0].equalsIgnoreCase("spawn")) {
            throw new IllegalArgumentException("Invalid command");
        }

        String entity = parts[1];
        if (!entity.equalsIgnoreCase("enemy")) {
            throw new IllegalArgumentException("Unknown entity: " + entity);
        }

        float[] location = new float[2];
        location[0] = Float.parseFloat(parts[2]);//X
        location[1] = Float.parseFloat(parts[3]);//Y

        return new SpawnEnemyCommand(location);
    }
}
