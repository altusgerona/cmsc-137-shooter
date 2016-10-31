package Entities;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Entity {
	protected int delta = 0;
	protected Vector2f pos;
	protected Bullet[] bullets;
	protected boolean alive = true;
	protected int current = 0;
	protected int health = 3;
	
	public Entity (Vector2f pos) {
		this.pos = pos;
		
		//Initialize bullets
		bullets = new Bullet[100];
		for (int i=0; i<bullets.length; i++) {
			bullets[i] = new Bullet();
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		//Render the bullets currently fired
		for (Bullet b : bullets) {
			b.render(gc, g);
		}
	}
	
	public void update(GameContainer gc, int t) throws SlickException {
		
		//Measure the difference between two clicks so rapidfiring can be regulated
		delta += t; //Increment delta with the time it took for a user to left click.
		
		for (Bullet b: bullets) {
			b.update(t);
		}
	}
	
	public void fireBullet (Vector2f vec, Bullet b) {
		//Reset delta to 0 since the user has already left clicked
		delta = 0;
		
		//Subtract the current position of the Player in coordinates from the clicked area's coordinates
		if (Networking.ClientListener.playerInfo.get(States.States.playerId) != null) {
			vec.sub(Networking.ClientListener.playerInfo.get(States.States.playerId));
		} else {
			vec.sub(pos);
		}
		
		//Normalize the vector to get the unit vector pointing to the direction
		vec.normalise();
		
		//Initialize the current bullet with the current position of the Player and direction
		if (Networking.ClientListener.playerInfo.get(States.States.playerId) != null) {
			bullets[current] = b.init(Networking.ClientListener.playerInfo.get(States.States.playerId).copy(), vec);
		} else {
			bullets[current] = b.init(pos.copy(), vec);
		}
		current++;
		
		//Loop back through all the bullets. Infinite.
		if (current >= bullets.length) current = 0;
		
	}
	
	public Bullet[] getBullets() {
		return bullets;
	}
	
	public Vector2f getPos() {
		return pos;
	}
	
	public void isHit (Bullet[] targetBullet) {
		//Check every bullet whether it hit something or not
		for (Bullet b : targetBullet) {
			if (b.isActive() && b.collideWith(pos, 100)) {
				//Kill bullet
				b.setActive(false);
				//Lower health
				System.out.println(health);
				health -= b.getDamage();
				if (health < 1 && alive) die();
				
			}
		}
	}
	
	public void die() {
		alive = false;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
