package Packets;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StartSignal implements Serializable{
	public boolean start;
	
	public StartSignal(boolean start){
		this.start = start;
	}

}
