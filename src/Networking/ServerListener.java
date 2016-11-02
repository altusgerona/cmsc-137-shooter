package Networking;

import java.io.PrintWriter;
import java.util.Scanner;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;


import Packets.BulletFire;
import Packets.ChatMessage;
import Packets.PlayerUpdate;
import Packets.Position;
import Packets.StartSignal;


public class ServerListener implements SocketListener{
	public static int playerCount = 0;

	@Override
	public void connected(Connection con) {
		System.out.println("Client Connected");
		ConnectionManager.getInstance().addConnection(con);
	}

	@Override
	public void disconnected(Connection con) {
		System.out.println("Client Disconnected");
		ConnectionManager.getInstance().removeConnection(con);
	}

	@Override
	public void received(Connection con, Object object) {
		if(object instanceof ChatMessage) {
			ChatMessage msg = (ChatMessage) object;
			for(Connection c : ConnectionManager.getInstance().getConnections()) {
				c.sendTcp(msg);
			}
		}
		
		if (object instanceof Position) {
			Position pos = (Position) object;
			for (Connection c : ConnectionManager.getInstance().getConnections()) {
				c.sendTcp(pos);
			}
		}
		
		if (object instanceof BulletFire) {
			BulletFire bf = (BulletFire) object;
			for (Connection c : ConnectionManager.getInstance().getConnections()) {
				c.sendTcp(bf);
			}
		}
		
		if (object instanceof StartSignal) {
			StartSignal ss = (StartSignal) object;
			for (Connection c : ConnectionManager.getInstance().getConnections()) {
				c.sendTcp(ss);
			}
		}
		
		if (object instanceof PlayerUpdate) {
			playerCount++;
			System.out.println("I happened");
			
			//Write to a file the playerCount so all instances have access
			try {
				PrintWriter writer = new PrintWriter("game-metadata.txt", "UTF-8");
				writer.println(playerCount);
				writer.close();
			} catch (Exception e) {
				
			}
			PlayerUpdate pu = (PlayerUpdate) object;
			
			//Send playerId updates to latest added client
			ConnectionManager.getInstance().getConnections().get(playerCount-1).sendTcp(pu);;
			
		}

	}
	

}
