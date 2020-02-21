package canadair;

import io.jbotsim.core.Message;
import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import io.jbotsim.ui.icons.Icons;

public class Station extends Node {

    public Station() {
        setIcon(Icons.STATION);
        setIconSize(25);
        setCommunicationRange(120);
    }

    @Override
    public void onStart() {
        sendAll(new Message());
    }
    
    //envoie la position du canadair
    @Override
	public void onMessage(Message message) {
		
		super.onMessage(message);
		if(message.getContent() instanceof Point)
		{
			sendAll(message);
		}
    }
}