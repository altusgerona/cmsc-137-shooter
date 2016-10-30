package Packets;

import java.io.Serializable;

import org.newdawn.slick.geom.Vector2f;

import Entities.Bullet;

@SuppressWarnings("serial")
public class BulletFire implements Serializable{
	public Vector2f pos;
	public Bullet b;
	
	public BulletFire(Vector2f pos, Bullet b) {
		this.pos = pos;
		this.b = b;
	}

}
