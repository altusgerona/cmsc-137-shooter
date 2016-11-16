package Packets;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PlayerUpdate implements Serializable {
	public boolean updateCount;
	
	public PlayerUpdate (boolean updateCount) {
		this.updateCount = updateCount;
	}
}
