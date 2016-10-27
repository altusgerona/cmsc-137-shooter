import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppGameContainer app;
		
		try {
			
			app = new AppGameContainer(new ScalableGame(new MainGame(), 1280, 720, false));
			app.setDisplayMode(1280, 720, false);
			app.setVSync(true);
			app.setShowFPS(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}
