package Entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Footmen extends Entity{
	
	protected int footMenFR = 300;
	protected int moveSpeed = 10;
	private Player p;

	public Footmen(Vector2f pos, Player p) {
		super(pos);
		this.p = p;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//Do Entity.render()
		super.render(gc, g);
		
		//Draw Footmen
		g.setColor(Color.black);
		g.fillRect(pos.getX()-20, pos.getY()-20, 40, 40);
		
	}
	
	public void update(GameContainer gc, int t) throws SlickException {
		
		//Get the Player's current location
		//Subtract the enemy's position from it
		//Get the unit vector from the result
		//Scalar multiply the vector by the ratio of update delta t and the Footmen's movespeed
		//Add the newly created vector to the current position of the enemy.
		pos.add(p.getPos().copy().sub(pos).normalise().scale((float)t/moveSpeed));
	}
	
	public void init(GameContainer gc) throws SlickException {
		health=1;
	}
	
}
