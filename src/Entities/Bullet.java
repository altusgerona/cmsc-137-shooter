package Entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Bullet {
	protected Vector2f pos;
	protected Vector2f dir;
	protected boolean active = true;
	protected int damage = 1;
	protected int airtime = 0;
	protected int maxAirTime = 2000;
	
	public Bullet (Vector2f pos, Vector2f dir) {
		this.pos = pos;
		this.dir = dir;
		dir.scale(500);
	}
	
	public Bullet init (Vector2f pos, Vector2f dir) {
		this.pos = pos;
		this.dir = dir;
		dir.scale(500);
		setActive(true);
		return this;
	}
	
	public Bullet() {
		active = false;
	}
	
	public void update (int t) {
		if (active) {
			Vector2f speed = dir.copy();
			speed.scale((t/1000.0f));
			pos.add(speed);
			airtime += t;
			if (airtime > maxAirTime) active = false;
			
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (active) {
			//Draw bullet here
			g.setColor(Color.red);
			g.fillOval(pos.getX()-10, pos.getY()-10, 20, 20);
		}
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive (boolean active) {
		this.active = active;
	}
	
	public boolean collideWith (Vector2f targetPos, int targetSize) {
		int distance = (int) targetPos.copy().sub(pos).lengthSquared();
		if (distance < (targetSize + 100)) return true;
		else return false;
	}
	
	public int getDamage() {
		return damage;
	}
	
}
