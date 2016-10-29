package States;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Entities.Player;

public class GameState extends BasicGameState{
	
	private Player p;

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		p = new Player(new Vector2f(200, 200));
		p.init(gc);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, States.GAME_HEIGHT, States.GAME_WIDTH);
		g.drawString("Game State sample", 50, 50);
		
		//Render player
		p.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int t) throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
			s.enterState(States.MENU);
		}
		p.update(gc, s, t);
	}

	@Override
	public int getID() {
		return States.GAME;
	}

}
