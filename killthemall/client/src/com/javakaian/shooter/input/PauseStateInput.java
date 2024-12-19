package com.javakaian.shooter.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.javakaian.states.HelpState;
import com.javakaian.states.MenuState;
import com.javakaian.states.PauseState;
import com.javakaian.states.State.StateEnum;

public class PauseStateInput extends InputAdapter {
    private PauseState pauseState;

    public  PauseStateInput(PauseState game) {
        this.pauseState = game;
    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode) {
            case Keys.SPACE:
                pauseState.getSc().setState(StateEnum.PLAY_STATE);
                break;
            case Keys.H:
                pauseState.getSc().setState(StateEnum.HELP_STATE);

            case Keys.P:
                pauseState.getSc().setState(StateEnum.PAUSE_STATE);
            case Keys.ESCAPE:
                pauseState.getSc().setState(StateEnum.PLAY_STATE);
            default:
                break;
        }

        return true;
    }
}
