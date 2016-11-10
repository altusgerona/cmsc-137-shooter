package States;

//import java.awt.Font;
//import java.util.Scanner;

//import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
//import org.newdawn.slick.UnicodeFont;
//import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

//import Networking.ClientStarter;
//import Packets.ChatMessage;

public class ChatState extends BasicGameState{
	private TextField usertf;
	public static String username;
	
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		usertf = new TextField(gc, gc.getDefaultFont(), 250, 250,300, 35);
	}

	

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		g.drawString("Enter Username:", 330, 200);
		usertf.render(gc, g);
		usertf.setFocus(true);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			username = usertf.getText();
			if(username != "" && !username.isEmpty()) {
				s.enterState(States.OPTION);
			}
		}
	}

	@Override
	public int getID() {
		return States.CHAT;
	}

}
