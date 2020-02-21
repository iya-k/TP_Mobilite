package broadcast;
import io.jbotsim.core.Message;
import io.jbotsim.core.Color;
import io.jbotsim.core.Node;
public class BroadcastNode extends Node {
	boolean informed;
	public void onStart() {
		informed = false;
		setColor(null);
	}
	public void onSelection() {
		informed = true;
		setColor(Color.RED);
		sendAll(new Message("My message"));
	}
	public void onMessage(Message message) {
		if (! informed) {
			informed = true;
			setColor(Color.RED);
			sendAll(new Message(message.getContent()));
		}
	}
}