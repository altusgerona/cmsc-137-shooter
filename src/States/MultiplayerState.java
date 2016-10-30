package States;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.jmr.wrapper.common.exceptions.NNClientCantConnect;

import Networking.ServerStarter;
import Networking.ClientStarter;

public class MultiplayerState extends BasicGameState {
	

	@Override
	public int getID() {
		return States.MULTIPLAYER;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
	}

	

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		
		
		g.drawString("Press C to start a server", 330, 200);
		g.drawString("Press J to join a server", 330, 250);
		g.drawString("Press S to start the game", 330, 300);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int t) throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_C)) {
			new ServerStarter();
		}
		
		if (gc.getInput().isKeyPressed(Input.KEY_J)) {
			try {
				new ClientStarter();
			} catch (NNClientCantConnect e) {
				System.out.println("No server found.");
				//Draw label saying no server found on screen
			}
		}
		
		if (gc.getInput().isKeyPressed(Input.KEY_S)) {
			s.enterState(States.GAME);
		}
		
	}

}
