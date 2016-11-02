package States;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import Entities.Footmen;
import Entities.Player;

public class GameState extends BasicGameState{
	
	private Player[] p = new Player[20];
	private LinkedList<Footmen> footMen;
	private Footmen f;
	private TextField tf;
	private boolean chatEnabled = false;
	private Random r;
	private int playerId = States.playerId;
	private int playerCount;
	
	
	public void enter(GameContainer gc, StateBasedGame s) throws SlickException {
		//Get necessary game metadata from file
		BufferedReader br;
		String currLine = null;
		try {
			br = new BufferedReader(new FileReader("game-metadata.txt"));
			currLine = br.readLine();
			br.close();
		} catch(Exception e) {
			
		}
		
		playerCount = Integer.parseInt(currLine);
		System.out.println("The playerCount is " + playerCount);
		

		for (int i=0; i<playerCount; i++){
			p[i] = new Player(new Vector2f(400, 300), i);
			p[i].init(gc);
			System.out.println("I initialize player with playerId "+p[i].playerId);
		}
		
		
		f = new Footmen(new Vector2f(150, 200), p[0]);
		System.out.println("My supposed playerID is "+States.playerId);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
				//Initialize the players
		
		
		
		//Initialize TextField for chat
		tf = new TextField(gc, gc.getDefaultFont(), 0, 580,800,25);
		
		
		//Initialize Footmen
		footMen = new LinkedList<Footmen>(); 
		
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		//Color the background
		g.setColor(Color.white);
		g.fillRect(0, 0, States.GAME_HEIGHT, States.GAME_WIDTH);
		
		//Render all players
		for (int i=0; i<playerCount; i++) {
			
			p[i].render(gc, g);
		}
		
		//Continue rendering enemy/s if they're still alive
		if (f.isAlive()) {
			f.render(gc, g);
			f.isHit(p[playerId].getBullets());
		}
		
		//Render TextField
		if (chatEnabled) {
			tf.render(gc, g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int t) throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
//			s.enterState(States.MENU);
			chatEnabled = true;
		}
		
		//Update all Players in every frame.
		for(int i=0; i<playerCount; i++) {
			p[i].update(gc, s, t);
		}
		
		//Update enemies in every frame.
		f.update(gc, t);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}


}
