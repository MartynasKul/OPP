package com.javakaian.shooter.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.javakaian.states.HelpState;
import com.javakaian.states.MenuState;
import com.javakaian.states.PauseState;
import com.javakaian.states.State.StateEnum;

public class HelpStateInput extends InputAdapter {
    private HelpState helpState;

    public  HelpStateInput(HelpState game) {
        this.helpState = game;
    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode) {
            case Keys.SPACE:
                helpState.getSc().setState(StateEnum.PLAY_STATE);
                break;
            case Keys.H:
                helpState.getSc().setState(StateEnum.HELP_STATE);

            default:
                break;
        }

        return true;
    }
}
