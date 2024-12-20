package com.javakaian.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.javakaian.shooter.utils.GameUtils;

public class DisconnectedState extends State {

    private BitmapFont smallFont;

    public DisconnectedState(StateController sc) {
        super(sc);
        smallFont = GameUtils.generateBitmapFont(32, Color.RED);
    }

    @Override
    public void render() {
        float red = 100f, green = 20f, blue = 20f;
        Gdx.gl.glClearColor(red / 255f, green / 255f, blue / 255f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        GameUtils.renderCenter("Disconnected", sb, bitmapFont);
        GameUtils.renderCenter("Press M for Menu or R to Retry", sb, smallFont, 0.6f);
        sb.end();
    }

    @Override
    public void update(float deltaTime) {
        // No gameplay updates needed here.
    }

    @Override
    public void dispose() {
        smallFont.dispose();
    }

    public void reconnect() {
        this.sc.setState(StateEnum.PLAY_STATE);
    }

    @Override
    protected void writeState(){System.out.println("You are in Disconnected State");}
}
