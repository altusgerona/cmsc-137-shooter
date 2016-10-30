package Networking;

import com.jmr.wrapper.common.exceptions.NNCantStartServer;
import com.jmr.wrapper.server.Server;

public class ServerStarter {
	public static Server server;
	
	public ServerStarter() {
		try {
			server = new Server(1337, 1337);
			server.setListener(new ServerListener());
			if (server.isConnected()) {
				System.out.println("Server started");
			}
		} catch (NNCantStartServer e) {
			e.printStackTrace();
		}
	}
}
