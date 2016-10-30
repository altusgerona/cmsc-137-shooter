package States;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState{
	

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
	}

	

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
//		g.setColor(Color.black);
//		g.fillRect(0, 0, States.GAME_HEIGHT, States.GAME_WIDTH);
		
		g.drawString("Survival Shooter", 330, 200);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_M)) {
			s.enterState(States.MULTIPLAYER);
		}
	}

	@Override
	public int getID() {
		return States.MENU;
	}

}
