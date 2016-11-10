package Networking;

//import java.util.Scanner;

import com.jmr.wrapper.client.Client;
import com.jmr.wrapper.common.exceptions.NNClientCantConnect;

//import Packets.ChatMessage;

public class ClientStarter {
	public static Client client;
	
	public ClientStarter() throws NNClientCantConnect {
		client = new Client("localhost", 1337, 1337);
		client.setListener(new ClientListener());
		client.connect();
	
		if (client.isConnected()) {
			System.out.println("Client Connected");
		}
		
	}
}
