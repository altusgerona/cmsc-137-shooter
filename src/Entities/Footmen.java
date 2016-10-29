package Entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Footmen extends Entity{
	
	protected int footMenFR = 300;
	
	private Player p;

	public Footmen(Vector2f pos) {
		super(pos);
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//Do Entity.render()
		super.render(gc, g);
		
		//Draw Footmen
		g.setColor(Color.black);
		g.fillRect(pos.getX()-20, pos.getY()-20, 40, 40);
		
	}
	
	public void update(GameContainer gc, int t) throws SlickException {
		
	}
	
}
