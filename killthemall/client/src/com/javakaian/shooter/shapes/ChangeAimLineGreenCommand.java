package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.javakaian.shooter.shapes.Command;

public class ChangeAimLineGreenCommand implements Command {

    private AimLine aimLine;

    public ChangeAimLineGreenCommand(AimLine aimLine) {
        this.aimLine = aimLine;
    }

    public void setColor(Color color){
        aimLine.setColor(color);
    }

    @Override
    public void execute() {
        setColor(Color.GREEN);
    }

    public void undo() {
        aimLine.setColor(Color.RED);
    }
}
