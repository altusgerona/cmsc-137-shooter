package Networking;

import org.newdawn.slick.geom.Vector2f;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;

import Entities.Bullet;
import Packets.BulletFire;
import Packets.PlayerUpdate;
import Packets.Position;
import Packets.StartSignal;

public class ClientListener implements SocketListener{
	public static Vector2f pos;
	public static Vector2f bulletPos;
	public static Bullet b;
	public static boolean ss;

	@Override
	public void connected(Connection con) {
		ClientStarter.client.getServerConnection().sendTcp(new PlayerUpdate(true));
	}

	@Override
	public void disconnected(Connection arg0) {
		
	}

	@Override
	public void received(Connection con, Object object) {
		if (object instanceof Position) {
			pos = ((Position) object).pos;
		}
		
		if (object instanceof BulletFire) {
			bulletPos = ((BulletFire) object).pos;
			b = ((BulletFire) object).b;
		}
		
		if (object instanceof StartSignal) {
			ss = ((StartSignal) object).start;
		}
		
		if (object instanceof PlayerUpdate) {
			if (((PlayerUpdate) object).updateCount) {
				States.States.playerCount++;
				States.States.playerId=States.States.playerCount-1;
				System.out.println(States.States.playerCount);
			}
		}
	}
	

}
