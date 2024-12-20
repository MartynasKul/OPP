package com.javakaian.states;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.javakaian.network.OClient;
import com.javakaian.network.messages.GameWorldMessage;
import com.javakaian.network.messages.LoginMessage;
import com.javakaian.network.messages.LogoutMessage;
import com.javakaian.network.messages.PlayerDied;
import com.javakaian.network.messages.PositionMessage;
import com.javakaian.network.messages.PositionMessage.DIRECTION;
import com.javakaian.network.messages.ShootMessage;
import com.javakaian.shooter.OMessageListener;
import com.javakaian.shooter.Strategy.BlinkingRedStrategy;
import com.javakaian.shooter.Strategy.DesaturationStrategy;
import com.javakaian.shooter.Strategy.GradientStrategy;
import com.javakaian.shooter.input.PlayStateInput;
import com.javakaian.shooter.shapes.*;
import com.javakaian.shooter.shapes.ColorController;
import com.javakaian.shooter.shapes.Iterator.GameEntityCollection;
import com.javakaian.shooter.shapes.Iterator.GameIterator;
import com.javakaian.shooter.utils.GameConstants;
import com.javakaian.shooter.utils.GameUtils;
import com.javakaian.shooter.utils.OMessageParser;

import static java.lang.Math.abs;
//import com.javakaian.states.ColorController;

/**
 * This is the state where gameplay happens.
 * 

 */
public class PlayState extends State implements OMessageListener {

	private Player player;
	private List<Player> players;
	private List<BaseEnemy> enemies;
	private List<Bullet> bullets;
	private AimLine aimLine;
	private int mapColor;
	private OClient myclient;
	private List<Mine> mines;
	private BitmapFont healthFont;
	private SoundPlayer soundPlayer;
	private GameEntityCollection collection;
	private long lastUpdateTime = 0;
	private ClientEntityGroup entityGroup;
	private float[] collisionCoords;
	private ParticleManager particleManager;


	public PlayState(StateController sc) {
		super(sc);

		init();
		ip = new PlayStateInput(this);
		healthFont = GameUtils.generateBitmapFont(20, Color.WHITE);
	}

	private void init() {

        particleManager = new ParticleManager("circle");

		collisionCoords = new float[2];
		entityGroup = new ClientEntityGroup();
		collection = new GameEntityCollection();
		ISoundPlayer soundAdapter = new SoundAdapter();
		soundPlayer = new SoundPlayer();
		soundPlayer.setPlayer(soundAdapter);

		myclient = new OClient(sc.getInetAddress(), this);
		myclient.connect();

		players = new ArrayList<>();
		enemies = new ArrayList<>();
		bullets = new ArrayList<>();
		mines = new ArrayList<>();
		aimLine = new AimLine(new Vector2(0, 0), new Vector2(0, 0));
		entityGroup.add(aimLine);
		
		aimLine.setCamera(camera);
		initializeRandomMines(6);
		LoginMessage m = new LoginMessage();
		m.setX(new SecureRandom().nextInt(GameConstants.SCREEN_WIDTH));
		m.setY(new SecureRandom().nextInt(GameConstants.SCREEN_HEIGHT));
		myclient.sendTCP(m);

	}

	@Override
	public void render() {
		sr.setProjectionMatrix(camera.combined);
		camera.update();
		if (player == null)
			return;

		ScreenUtils.clear(0, 0, 0, 1);

		sr.begin(ShapeType.Line);
		particleManager.render(sr);
		entityGroup.render(sr);

		//sr.setColor(Color.RED);
		//players.forEach(p -> p.render(sr));
		//sr.setColor(Color.WHITE);
		//enemies.forEach(e -> e.render(sr));
		//bullets.forEach(b -> b.render(sr));
		//mines.forEach((m -> m.render(sr)));
		//sr.setColor(Color.BLUE);
		//player.render(sr);
		//sr.setColor(Color.WHITE);
		//aimLine.render(sr);
		followPlayer();
		sr.end();

		sb.begin();
		GameUtils.renderCenter("HEALTH: " + player.getHealth(), sb, healthFont, 0.1f);
		sb.end();

		//render enemies based on closeness to the player( if enemy close to player = > render
	}

	/**
	 * This function let camera to follow player smootly.
	 */
	private void followPlayer() {
		float lerp = 0.05f;
		camera.position.x += (player.getPosition().x - camera.position.x) * lerp;
		camera.position.y += (player.getPosition().y - camera.position.y) * lerp;
	}
	private void initializeRandomMines(int count) {
		Random random = new Random();
	
		for (int i = 0; i < count; i++) {
			float x = random.nextInt(GameConstants.SCREEN_WIDTH); // Random x-coordinate
			float y = random.nextInt(GameConstants.SCREEN_HEIGHT); // Random y-coordinate
			Mine mine = new Mine(x, y, 20); // Create mine with random position and size 20
			mines.add(mine); // Add to the mines list
			//entityGroup.add(mine);
		}
	}	
	@Override
	public void update(float deltaTime) {
		if (player == null)
			return;
		aimLine.setBegin(player.getCenter());
		aimLine.update(deltaTime);
		particleManager.update(deltaTime);
		
		processInputs();

		enemies.stream()
				.filter(Objects::nonNull)
				.filter(e->abs(e.getX()-player.getPosition().x)<=30 &&
						   abs(e.getY()-player.getPosition().y)<=30)
				.forEach(e->{
					e.performAction();
					soundPlayer.playSound("client/assets/sounds/alert.wav");
					}
				);


		//enemies.forEach(e-> e.performAction());
		//for(BaseEnemy e :enemies) {
		//	if(e!=null){
		//		if(abs(e.getX()-player.getPosition().x) <= 2 && abs(e.getY()-player.getPosition().y) <= 2){
		//			e.performAction();
		//		}
		//	}
		//}
	}

	public void scrolled(float amountY) {
		if (amountY > 0) {
			camera.zoom += 0.2;
		} else {
			if (camera.zoom >= 0.4) {
				camera.zoom -= 0.2;
			}
		}
	}

	/**
	 * This should be called when player shoot a bullet. It sends a shoot message to
	 * the server with angle value.
	 */
	public void shoot() {
		ShootMessage m = new ShootMessage();
		m.setId(player.getId());
		m.setAngleDeg(aimLine.getAngle());
		myclient.sendUDP(m);
		soundPlayer.playSound("client/assets/sounds/bulletShoot.wav");
	}

	/**
	 * Whenever player press a button, this creates necessary position message and
	 * sends it to the server.
	 */
	private void processInputs() {
		PositionMessage p = new PositionMessage();
		p.setId(player.getId());
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			p.setDirection(DIRECTION.DOWN);
			myclient.sendUDP(p);
		}
		if (Gdx.input.isKeyPressed(Keys.B)) {
			ColorController cc = new ColorController(aimLine);
			cc.changeColorBlue();
		}

		if (Gdx.input.isKeyPressed(Keys.G)) {
			ColorController cc = new ColorController(aimLine);
			cc.changeColorGreen();
		}

		if (Gdx.input.isKeyPressed(Keys.N)) {
			ColorController cc = new ColorController(aimLine);
			cc.undo();
		}
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			p.setDirection(DIRECTION.UP);
			myclient.sendUDP(p);
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			p.setDirection(DIRECTION.LEFT);
			myclient.sendUDP(p);
		}
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			p.setDirection(DIRECTION.RIGHT);
			myclient.sendUDP(p);
		}
		if(Gdx.input.isKeyJustPressed(Keys.P)){
			player.update();
		}
		if(Gdx.input.isKeyPressed(Keys.O)){
			this.getSc().setState(StateEnum.PAUSE_STATE);
		}
		if(Gdx.input.isKeyPressed(Keys.H)){
			this.getSc().setState(StateEnum.HELP_STATE);
		}

	}

	@Override
	public void loginReceieved(LoginMessage m) {

		player = new Player(m.getX(), m.getY(), 50, "Player_", new GradientStrategy());
		player.setId(m.getId());
		player.setName("Player_"+player.getId());	
		entityGroup.add(player);
	}

	@Override
	public void logoutReceieved(LogoutMessage m) {
		// do the logout proccess here
		Scoreboard.getInstance().removePlayer(player.getId());
		this.getSc().setState(StateEnum.DISCONNECTED_STATE);
	}

	@Override
	public void playerDiedReceived(PlayerDied m) {
		if (player.getId() != m.getId())
			return;
		Scoreboard.getInstance().removePlayer(m.getId());
		LogoutMessage mm = new LogoutMessage();
		mm.setId(player.getId());
		myclient.sendTCP(mm);
		myclient.close();
		this.getSc().setState(StateEnum.GAME_OVER_STATE);


	}

	@Override
	public void gwmReceived(GameWorldMessage m) {
		long currentTime = System.currentTimeMillis();
		enemies = OMessageParser.getEnemiesFromGWM(m);
		bullets = OMessageParser.getBulletsFromGWM(m);
		players = OMessageParser.getPlayersFromGWM(m);
		collisionCoords = OMessageParser.getCollisionCoordsFromGwm(m);

		if (collisionCoords[0] != 0.0 && collisionCoords[1] != 0.0)
		{
			particleManager.spawnParticle(collisionCoords[0], collisionCoords[1], 1);

			collisionCoords[0] = 0.0f;
			collisionCoords[1] = 0.0f;
		}

		entityGroup.clear();
		for(BaseEnemy e : enemies){
			entityGroup.add(e);
		}
		for(Bullet b : bullets){
			entityGroup.add(b);
		}
		for(Mine mine : mines){
			entityGroup.add(mine);
		}
		for(Player p : players){
			entityGroup.add(p);
		}

		if(currentTime-lastUpdateTime >=100000){
			
			lastUpdateTime = currentTime;

			for(BaseEnemy e : enemies){
				collection.addEnemy(e);
			}
			for(Bullet b : bullets){
				collection.addBullet(b);
			}
			for(Player p : players){
				collection.addPlayer(p);
			}
			GameIterator<Player> playerIterator = collection.getPlayerIterator();
			GameIterator<Bullet> bulletIterator = collection.getBulletIterator();
			GameIterator<BaseEnemy> enemyIterator = collection.getEnemyIterator();
			while(playerIterator.hasNext()){
				Player p = playerIterator.next();
				System.out.println("Player: " + p.getName());
			}
			while(bulletIterator.hasNext()){
				Bullet b = bulletIterator.next();
				System.out.println("Bullet positione: " + b.getPosition());
			}
			while(enemyIterator.hasNext()){
				BaseEnemy e = enemyIterator.next();
				System.out.println("Enemy " + e.getShape() + " in position" + e.getX() + " " + e.getY() );
			}
		}

		mapColor = OMessageParser.getMapFromGWM(m);
		if (player == null)
			return;
		// Find yourself.
		players.stream().filter(p -> p.getId() == player.getId()).findFirst().ifPresent(p -> player = p);
		// Remove yourself from playerlist.
		players.removeIf(p -> p.getId() == player.getId());

	}

	public void restart() {
		init();
	}

	@Override
	public void dispose() {

		LogoutMessage m = new LogoutMessage();
		m.setId(player.getId());
		myclient.sendTCP(m);
	}

	@Override
	protected void writeState(){System.out.println("You are in Play State");}
}