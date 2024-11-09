package com.javakaian.shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.javakaian.shooter.shapes.Scoreboard;
import com.javakaian.shooter.utils.ScoreboardRenderer;
import com.javakaian.states.State.StateEnum;
import com.javakaian.states.StateController;
import com.javakaian.shooter.utils.ScoreboardRenderer;

import java.util.HashMap;
import java.util.Map;

public class KillThemAll extends ApplicationAdapter {
	private StateController sc;


	private String inetAddress;
	ScoreboardRenderer sr;




	public KillThemAll(String inetAddress) {
		this.inetAddress = inetAddress;
	}

	@Override
	public void create() {

		sc = new StateController(inetAddress);
		sc.setState(StateEnum.MENU_STATE);
		sr = new ScoreboardRenderer();


	}
	public void updateScoreboard(Map<String, Integer> playerScores){
		sr.updateScores(playerScores);
	}

	@Override
	public void render() {
		sc.render();
		sc.update(Gdx.graphics.getDeltaTime());
		sr.render();


	}

	@Override
	public void dispose() {
		super.dispose();
		sc.dispose();
		sr.dispose();
	}

}
