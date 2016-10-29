package Entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Entity{
	
	protected int fireRate = 200;

	public Player(Vector2f pos) {
		super(pos);
		
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (!isAlive()) return;
		super.render(gc, g);
		
		g.setColor(Color.blue);
		g.fillRect(pos.getX()-30, pos.getY()-30, 60, 60);
		
		//health bar here
		
	}
	
	public void update(GameContainer gc, StateBasedGame s, int t) throws SlickException {
		super.update(gc, t);
		if( gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && delta > fireRate) {
			fireBullet(new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY()), new Bullet());
			System.out.println("Fire"+fireRate);
			
		}
		
		float deltaLenght = (float)t/5;
		
		//Controls 
		
		if( ( gc.getInput().isKeyDown(Input.KEY_RIGHT) || gc.getInput().isKeyDown(Input.KEY_D) ) && 
				pos.getX() < 820  ) {
		
			pos.add( new Vector2f(deltaLenght,0) );
		}
		if( ( gc.getInput().isKeyDown(Input.KEY_LEFT) || gc.getInput().isKeyDown(Input.KEY_A) ) && 
				pos.getX() > -20  ) {
		
			pos.add( new Vector2f(-deltaLenght,0) );
		}
		if( ( gc.getInput().isKeyDown(Input.KEY_DOWN) || gc.getInput().isKeyDown(Input.KEY_S) ) && 
				pos.getY() < 620  ) {
		
			pos.add( new Vector2f(0,deltaLenght) );
		}
		if( ( gc.getInput().isKeyDown(Input.KEY_UP) || gc.getInput().isKeyDown(Input.KEY_W) ) && 
				pos.getY() > -20  ) {
		
			pos.add( new Vector2f(0,-deltaLenght) );
		}
		
	}
	
	public void die(StateBasedGame s) {
		s.enterState(1);
	}

	public void init(GameContainer gc) {
		super.init(gc);
		
	}


}
