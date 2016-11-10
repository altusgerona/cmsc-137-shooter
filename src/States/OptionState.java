package States;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Packets.ChatMessage;

public class OptionState extends BasicGameState{
	private TextField tf;
	private static TextField msgtf;
	private String username;
	
	Image start;
	Image leaderboard;
	Image howToPlay;
	
	public void enter(GameContainer gc, StateBasedGame s) throws SlickException {
		username = ChatState.username;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		tf = new TextField(gc, gc.getDefaultFont(), 0, 580,800,25);
		setMsgtf(new TextField(gc, gc.getDefaultFont(), 0, 400,800,185));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		//Color the background		
		g.drawString("Hello Player!", 330, 50);
		g.drawString("Survive and shoot at others while", 230, 100);
		g.drawString("trying to keep your own tank alive!", 230, 130);
		start = new Image("resources/start.png");
		leaderboard = new Image("resources/leaderboard.png");
		howToPlay = new Image("resources/howToPlay.png");
		g.drawImage(start, 330, 170);
		g.drawImage(leaderboard, 150, 300);
		g.drawImage(howToPlay, 500, 300);
		
		tf.render(gc, g);
		
		getMsgtf().render(gc, g);
		getMsgtf().setBackgroundColor(null);
		getMsgtf().setBorderColor(null);
		getMsgtf().setTextColor(Color.blue);
		getMsgtf().deactivate();
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int t) throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		if ((posX>338 && posX<421) && (posY>338 && posY<424)) {
			if(Mouse.isButtonDown(0)){
				s.enterState(States.GAME);
			}
		}
		tf.setFocus(true);
		
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			String inFromClient = tf.getText();
			if(inFromClient != "" && !inFromClient.isEmpty()) {
					tf.setText("");
					ChatMessage msg = new ChatMessage(username, inFromClient);
					Networking.ClientStarter.client.getServerConnection().sendTcp(msg);
			}
		}		
	}

	@Override
	public int getID() {
		return States.OPTION;
	}
	
	public static TextField getMsgtf() {
		return msgtf;
	}

	public void setMsgtf(TextField msgtf) {
		OptionState.msgtf = msgtf;
	}

}
