import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
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
			
			app = new AppGameContainer(new Main());
			app.setDisplayMode(States.GAME_HEIGHT, States.GAME_WIDTH, false);
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
		
//		new Resources();
		
		this.addState(new MenuState());
		this.addState(new GameState());
		this.addState(new MultiplayerState());
		this.addState(new ChatState());
		this.addState(new OptionState());
	}

}
