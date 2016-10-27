package States;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState{

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		g.drawString("Menu State sample", 50, 50);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return States.MENU;
	}

}
