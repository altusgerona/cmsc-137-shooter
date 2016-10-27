import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame{

	public Main() {
		super("AaronBurr");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppGameContainer app;
		
		try {
			
			app = new AppGameContainer(new ScalableGame(new Main(), 1280, 720, false));
			app.setDisplayMode(1280, 720, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setTargetFrameRate(60);
		gc.setAlwaysRender(true);
		gc.setMaximumLogicUpdateInterval(60);
		gc.setShowFPS(true);
		gc.setVSync(true);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
