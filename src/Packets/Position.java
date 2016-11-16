package Packets;

import java.io.Serializable;

import org.newdawn.slick.geom.Vector2f;

@SuppressWarnings("serial")
public class Position implements Serializable {
	public Vector2f pos;
	public int playerId;
	
	public Position(Vector2f pos, int playerId) {
		this.pos = pos;
		this.playerId = playerId;
	}

}
