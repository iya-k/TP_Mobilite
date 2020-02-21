package loner;

import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;
public class LonerMessageBased extends Node {
	private boolean heardSomeone = false;
	public void onClock(){
		if (heardSomeone){
			setColor(Color.red);
		} else {
			setColor(Color.green);
		}
		heardSomeone = false;
		sendAll(new Message());
	}
	public void onMessage(Message msg) {
		heardSomeone = true;
	}
}
