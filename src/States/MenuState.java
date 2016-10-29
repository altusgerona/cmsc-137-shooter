package States;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState{
	
	private TextField tf;
	private UnicodeFont font = getNewFont("Arial" , 16);

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
		font.loadGlyphs();
		tf = new TextField(gc, font, 50,50,100,25);
	}

	@SuppressWarnings("unchecked")
	private UnicodeFont getNewFont(String fontName, int fontSize) {
		UnicodeFont returnFont = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        returnFont.addAsciiGlyphs();
        returnFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (returnFont);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, States.GAME_HEIGHT, States.GAME_WIDTH);
		tf.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		System.out.println(tf.getText());
	}

	@Override
	public int getID() {
		return States.MENU;
	}

}
