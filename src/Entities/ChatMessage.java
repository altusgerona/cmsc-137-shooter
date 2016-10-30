package Entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ChatMessage implements Serializable {
	public String username, message;
	
	public ChatMessage(String username, String message) {
		this.username = username;
		this.message = message;
	}

}
