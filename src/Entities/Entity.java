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
		bullets = new Bullet[20];
		for (int i=0; i<bullets.length; i++) {
			bullets[i] = new Bullet();
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (Bullet b : bullets) {
			b.render(gc, g);
		}
	}
	
	public void update(GameContainer gc, int t) {
		delta += t;
		
		for (Bullet b: bullets) {
			b.update(t);
		}
	}
	
	public void fireBullet (Vector2f vec, Bullet b) {
		delta = 0;
		vec.sub(pos);
		vec.normalise();
		bullets[current] = b.init(pos.copy(), vec);
		current++;
		if (current >= bullets.length) current = 0;
		
	}
	
	public Bullet[] getBullets() {
		return bullets;
	}
	
	public Vector2f getPos() {
		return pos;
	}
	
	public void isBulletCollide (Bullet[] targetBullet) {
		for (Bullet b : targetBullet) {
			if (b.isActive() && b.collideWith(pos, 100)) {
				//Kill bullet
				b.setActive(false);
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

	public void init(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}
}
