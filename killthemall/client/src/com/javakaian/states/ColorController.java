package com.javakaian.shooter.shapes;

import com.badlogic.gdx.graphics.Color;
import com.javakaian.shooter.shapes.ChangeAimLineBlueCommand;
import com.javakaian.shooter.shapes.Command;

public class ColorController {

    private AimLine aimLine;
    private Color previousColor;

    public ColorController(AimLine aimLine) {
        this.aimLine = aimLine;
        this.previousColor = aimLine.getColor();
    }

    public void changeColorBlue() {
        Command changeColorCommand = new ChangeAimLineBlueCommand(aimLine);
        changeColorCommand.execute();
    }

    public void changeColorGreen() {
        Command changeColorCommand = new ChangeAimLineGreenCommand(aimLine);
        changeColorCommand.execute();
    }

    public void undo() {
        Command changeColorCommand = new ChangeAimLineBlueCommand(aimLine);
        changeColorCommand.undo();
    }
}
