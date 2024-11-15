package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.shooter.shapes.Command;

public class ChangeAimLineBlueCommand implements Command {

    private AimLine aimLine;

    public ChangeAimLineBlueCommand(AimLine aimLine) {
        this.aimLine = aimLine;
    }

    public void setColor(Color color){
        aimLine.setColor(color);
    }

    @Override
    public void execute() {
        setColor(Color.BLUE);
    }

    public void undo() {
        aimLine.setColor(Color.RED);
    }
}
