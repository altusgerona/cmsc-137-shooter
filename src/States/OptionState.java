package States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class OptionState extends BasicGameState{
	
	public void enter(GameContainer gc, StateBasedGame s) throws SlickException {
	
	}

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
	
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		//Color the background
		g.setColor(Color.white);
		g.fillRect(0, 0, States.GAME_HEIGHT, States.GAME_WIDTH);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int t) throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {			
			s.enterState(States.GAME);
		}
	}

	@Override
	public int getID() {
		return States.OPTION;
	}
}
