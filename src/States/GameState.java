package States;

import java.io.BufferedReader;
import java.io.FileReader;
//import java.util.LinkedList;
//import java.util.Random;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Entities.Footmen;
import Entities.Player;
import Packets.ChatMessage;

public class GameState extends BasicGameState{
	
	private Player[] p = new Player[20];
	private Footmen[] f = new Footmen[20];
	//private LinkedList<Footmen> footMen;
//	private Footmen f;
	private TextField tf;
	private static TextField msgtf;
	private boolean chatEnabled = false;
//	private Random r;
	private int playerId = States.playerId;
	private int playerCount;
	private int polygonCount;
	private String username;	
	
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
		polygonCount = 20;
		
		for (int i=0; i<playerCount; i++){
			p[i] = new Player(new Vector2f(400, 300), i);
			p[i].init(gc);
			System.out.println("I initialize player with playerId "+p[i].playerId);
			username = ChatState.username;
		}
		
		for (int i=0; i<polygonCount; i++){
			Random rand = new Random();
			int x = rand.nextInt(800) + 1;
			int y = rand.nextInt(600) + 1;
			f[i] = new Footmen(new Vector2f(x, y), p[i]);
		}
	}

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
				//Initialize the players
		
		//Initialize TextField for chat
		tf = new TextField(gc, gc.getDefaultFont(), 0, 580,800,25);
		setMsgtf(new TextField(gc, gc.getDefaultFont(), 0, 400,800,185));

		
		//Initialize Footmen
		//footMen = new LinkedList<Footmen>(); 
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		//Color the background
		g.setColor(Color.white);
		g.fillRect(0, 0, States.GAME_HEIGHT, States.GAME_WIDTH);
		
		Image background = new Image("resources/background.png");
		g.drawImage(background, 0, 0);
		
		//Continue rendering enemy/s if they're still alive
		for (int i=0; i<polygonCount; i++) {	
			if (f[i].isAlive()) {
				f[i].render(gc, g);
				f[i].isHit(p[playerId].getBullets());
			} else {
				Random rand = new Random();
				int x = rand.nextInt(600) + 1;
				int y = rand.nextInt(600) + 1;
				f[i] = new Footmen(new Vector2f(x, y), p[i]);
			}
		}
		
		//Render TextField
		if (chatEnabled) {
			tf.render(gc, g);
		}
		
		getMsgtf().render(gc, g);
		getMsgtf().setBackgroundColor(null);
		getMsgtf().setBorderColor(null);
		getMsgtf().deactivate();
		

		//Render all players
		for (int i=0; i<playerCount; i++) {	
			p[i].render(gc, g);
			g.drawString(username, 370, 500);
			g.drawRoundRect(220, 520, 350, 30, 5);
			g.drawString("Score: 20", 370, 525);
			g.drawRoundRect(205, 560, 380, 30, 5);
			g.drawString("Level 1", 370, 565);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int t) throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
//			s.enterState(States.MENU);
			if(chatEnabled) {
				String inFromClient = tf.getText();
				if(inFromClient != "" && !inFromClient.isEmpty()) {
					tf.setText("");
					ChatMessage msg = new ChatMessage(username, inFromClient);
					Networking.ClientStarter.client.getServerConnection().sendTcp(msg);
				}
				chatEnabled = false;
			} else {
				tf.setText("");
				tf.setFocus(true);
				chatEnabled = true;
			}
		}
		
		if(chatEnabled) {
			tf.setFocus(true);
		}
		
		//Update all Players in every frame.
		for(int i=0; i<playerCount; i++) {
			p[i].update(gc, s, t, chatEnabled);
		}
		
		//Update enemies in every frame.
		for(int i=0; i<polygonCount; i++) {
			f[i].update(gc, t);
		}
		
	}

	@Override
	public int getID() {
		return 0;
	}

	public static TextField getMsgtf() {
		return msgtf;
	}

	public void setMsgtf(TextField msgtf) {
		GameState.msgtf = msgtf;
	}


}
