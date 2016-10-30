package Entities;

import java.io.Serializable;

import org.newdawn.slick.geom.Vector2f;

@SuppressWarnings("serial")
public class BulletPosition implements Serializable{
	public Vector2f pos;
	
	public BulletPosition(Vector2f pos) {
		this.pos = pos;
	}

}
