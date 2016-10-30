package Networking;

import org.newdawn.slick.geom.Vector2f;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;

import Entities.Position;

public class ClientListener implements SocketListener{
	public static Vector2f pos;

	@Override
	public void connected(Connection arg0) {
		
	}

	@Override
	public void disconnected(Connection arg0) {
		
	}

	@Override
	public void received(Connection con, Object object) {
		if (object instanceof Position) {
			pos = ((Position) object).pos;
		}
	}
	

}
