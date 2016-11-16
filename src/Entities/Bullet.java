package Entities;

import java.io.Serializable;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

@SuppressWarnings("serial")
public class Bullet implements Serializable{
	protected Vector2f pos;
	protected Vector2f dir;
	protected boolean active = true;
	protected int damage = 1;
	protected int airtime = 0;
	protected int maxAirTime = 2000;
	protected float bulletSpeed = 1000; //Lower is faster
	
	public Bullet (Vector2f pos, Vector2f dir) {
		this.pos = pos;
		this.dir = dir;
		
		//Scalar multiply the unit vector or direction of the bullet
		dir.scale(500);
	}
	
	public Bullet init (Vector2f pos, Vector2f dir) {
		this.pos = pos;
		this.dir = dir;
		
		//scalar multiply the unit vector or direction of the bullet
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
			
			//Increase the speed variable that would increment its position
			speed.scale((t/bulletSpeed));
			
			//Increment the position of the bullet with a vector of the same direction, speed
			pos.add(speed);
			Networking.ClientStarter.client.getServerConnection().sendTcp(pos);
			
			//Limit the rendering of a bullet so it may be deleted off screen
			airtime += t;
			if (airtime > maxAirTime) active = false;
			
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (active) {
			//Draw bullet
			g.setColor(Color.red);
			
			if (Networking.ClientListener.bulletPos != null) {
				g.fillOval(Networking.ClientListener.bulletPos.x-10, Networking.ClientListener.bulletPos.y-10, 20, 20);
			} else {
				g.fillOval(pos.getX()-10, pos.getY()-10, 20, 20);
			}
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
