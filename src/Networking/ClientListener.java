package Networking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
//import java.util.Scanner;

import org.newdawn.slick.geom.Vector2f;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;


import Entities.Bullet;
import Packets.BulletFire;
import Packets.ChatMessage;
import Packets.PlayerUpdate;
import Packets.Position;
import Packets.StartSignal;
//import States.ChatState;

public class ClientListener implements SocketListener{
	public static Map<Integer, Vector2f> playerInfo= new HashMap<Integer, Vector2f>();
	public static Vector2f bulletPos;
	public static Bullet b;
	public static int playerId;
	public static int chatCounter = 0;
	public static boolean ss;

	@Override
	public void connected(Connection con) {
		ClientStarter.client.getServerConnection().sendTcp(new PlayerUpdate(true));
	}

	@Override
	public void disconnected(Connection con) {
	}

	@Override
	public void received(Connection con, Object object) {
		if(object instanceof ChatMessage) {
			ChatMessage msg = (ChatMessage) object;
			String currMessage = States.GameState.getMsgtf().getText();
			
			if(chatCounter < 8) {
				States.GameState.getMsgtf().setText(currMessage + "\n" + msg.username + ":" + msg.message);
				States.OptionState.getMsgtf().setText(currMessage + "\n" + msg.username + ":" + msg.message);
			} else {
				String[] result = currMessage.split("\n", 2);
				States.GameState.getMsgtf().setText(result[1] + "\n" + msg.username + ":" + msg.message);
				States.OptionState.getMsgtf().setText(result[1] + "\n" + msg.username + ":" + msg.message);
			}
			chatCounter++;
		}
		
		if (object instanceof Position) {
			playerInfo.put(((Position) object).playerId, ((Position) object).pos);
		}
		
		if (object instanceof BulletFire) {
			bulletPos = ((BulletFire) object).pos;
			b = ((BulletFire) object).b;
			playerId = ((BulletFire) object).playerId;
		}
		
		if (object instanceof StartSignal) {
			ss = ((StartSignal) object).start;
		}
		
		if (object instanceof PlayerUpdate) {
			if (((PlayerUpdate) object).updateCount) {
				BufferedReader br;
				String currLine = null;
				try {
					br = new BufferedReader(new FileReader("game-metadata.txt"));
					currLine = br.readLine();
					br.close();
				} catch(Exception e) {
					
				}
				
				States.States.playerId=Integer.parseInt(currLine)-1;
			}
		}
		

	}
	

}
