package States;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class OptionState extends BasicGameState{
	
	Image start;
	Image leaderboard;
	Image howToPlay;
	
	public void enter(GameContainer gc, StateBasedGame s) throws SlickException {
	
	}

	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
	
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
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int t) throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		System.out.println("x: " + posX);
		System.out.println("y: " + posY);
		if ((posX>386 && posX<421) && (posY>338 && posY<424)) {
			
			if(Mouse.isButtonDown(0)){
				s.enterState(States.GAME);
			}
		}
	}

	@Override
	public int getID() {
		return States.OPTION;
	}
}
