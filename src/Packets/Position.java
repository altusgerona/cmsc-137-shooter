package Packets;

import java.io.Serializable;

import org.newdawn.slick.geom.Vector2f;

@SuppressWarnings("serial")
public class Position implements Serializable {
	public Vector2f pos;
	
	public Position(Vector2f pos) {
		this.pos = pos;
	}

}
