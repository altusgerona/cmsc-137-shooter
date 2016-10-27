import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import States.*;

public class Main extends StateBasedGame{

	public Main() {
		super("AaronBurr");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppGameContainer app;
		
		try {
			
			app = new AppGameContainer(new ScalableGame(new Main(), 1280, 720, false));
			app.setDisplayMode(800, 600, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setTargetFrameRate(60);
		gc.setAlwaysRender(true);
		gc.setMaximumLogicUpdateInterval(60);
		gc.setShowFPS(false);
		gc.setVSync(true);
		
		this.addState(new GameState());
		this.addState(new MenuState());
	}

}
