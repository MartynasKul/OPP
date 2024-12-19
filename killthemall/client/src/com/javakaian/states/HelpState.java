package com.javakaian.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.javakaian.shooter.input.HelpStateInput;
import com.javakaian.shooter.input.MenuStateInput;
import com.javakaian.shooter.utils.GameUtils;

public class HelpState extends State {

    private BitmapFont smallFont;

    public HelpState(StateController sc) {
        super(sc);
        ip = new HelpStateInput(this);
        smallFont = GameUtils.generateBitmapFont(24, Color.WHITE);
    }

    @Override
    public void render() {
        float red = 20f, green = 50f, blue = 80f;
        Gdx.gl.glClearColor(red / 255f, green / 255f, blue / 255f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        GameUtils.renderCenter("Help Menu", sb, bitmapFont);
        GameUtils.renderCenter("WASD/Arrow Keys: Move", sb, smallFont, 0.5f);
        GameUtils.renderCenter("Space: Shoot | M: Menu", sb, smallFont, 0.6f);
        GameUtils.renderCenter("Q: Quit | R: Restart", sb, smallFont, 0.7f);
        GameUtils.renderCenter("TO CHANGE POINTER COLOS : G B N", sb, smallFont, 0.8f);
        GameUtils.renderCenter("Press M for Menu", sb, smallFont, 0.9f);
        sb.end();
    }

    @Override
    public void update(float deltaTime) {
        // No updates needed for static help screen.
    }

    @Override
    public void dispose() {
        smallFont.dispose();
    }
}
