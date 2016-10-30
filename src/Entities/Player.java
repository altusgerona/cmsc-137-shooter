package Entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


import States.States;

public class Player extends Entity{
	
	protected int playerID; //Clients control only their player
	protected int fireRate = 200; //Smaller the better
	protected int moveSpeed = 5; //Lower is faster

	public Player(Vector2f pos) {
		super(pos);
		
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (!isAlive()) return;
		super.render(gc, g);
		
		g.setColor(Color.blue);
		
		if (Networking.ClientListener.pos != null) {
			g.fillRect(Networking.ClientListener.pos.x-30, Networking.ClientListener.pos.y-30, 60, 60);
		} else {
			g.fillRect(pos.getX()-30, pos.getY()-30, 60, 60);
		}
		
		//health bar here
		
	}
	
	public void update(GameContainer gc, StateBasedGame s, int t) throws SlickException {
		super.update(gc, t); //Run the Entity "parent"'s update()
		if( gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && delta > fireRate) {
			
			fireBullet(new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY()), new Bullet());
			
		}
		
		//Get the amount of distance to displace the Player
		//This distance would be equal to the ratio of the update delta t and the given moveSpeed
		float distance = (float)t/moveSpeed;
		
		//Controls 
		
		//Move right
		if((gc.getInput().isKeyDown(Input.KEY_RIGHT) || gc.getInput().isKeyDown(Input.KEY_D) ) && 
				pos.getX() < States.GAME_HEIGHT  ) {
			pos.add(new Vector2f(distance,0));
			Networking.ClientStarter.client.getServerConnection().sendTcp(new Position(pos));
		}
		
		//Move left
		if((gc.getInput().isKeyDown(Input.KEY_LEFT) || gc.getInput().isKeyDown(Input.KEY_A) ) && 
				pos.getX() > 0  ) {
		
			pos.add(new Vector2f(-distance,0));
			Networking.ClientStarter.client.getServerConnection().sendTcp(new Position(pos));
		}
		
		//Move down
		if((gc.getInput().isKeyDown(Input.KEY_DOWN) || gc.getInput().isKeyDown(Input.KEY_S) ) && 
				pos.getY() < States.GAME_WIDTH  ) {
		
			pos.add(new Vector2f(0,distance));
			Networking.ClientStarter.client.getServerConnection().sendTcp(new Position(pos));
			
		}
		
		//Move up
		if( ( gc.getInput().isKeyDown(Input.KEY_UP) || gc.getInput().isKeyDown(Input.KEY_W) ) && 
				pos.getY() > 0  ) {
		
			pos.add(new Vector2f(0,-distance));
			Networking.ClientStarter.client.getServerConnection().sendTcp(new Position(pos));
		}
		
	}
	
	public void die(StateBasedGame s) {
		s.enterState(1);
	}

	public void init(GameContainer gc) throws SlickException {
		super.init(gc);
		
	}

	


}
