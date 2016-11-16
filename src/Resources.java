import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
//import org.newdawn.slick.Sound;
//import org.newdawn.slick.SpriteSheet;

public class Resources {
	private static Map<String, Image> images;
//	private static Map<String, SpriteSheet> sprites;
//	private static Map<String, Sound> sounds;
	public Resources() {
		images = new HashMap<String, Image>();
//		sprites = new HashMap<String, SpriteSheet>();
//		sounds = new HashMap<String, Sound>();
		
		try {
			images.put("player", loadImage("res/path_to_image.png"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	private Image loadImage(String path) throws SlickException {
		return new Image(path, false, Image.FILTER_NEAREST);
	}
	
	public static Image getImage(String image) {
		return images.get(image);
	}
	
}
