package loner;

import io.jbotsim.core.Color;
import io.jbotsim.core.Link;
import io.jbotsim.core.Node;
public class LonerGraphBased extends Node{
	public void onStart() {
		if (getNeighbors().isEmpty()) {
			setColor(Color.green);
		} else {
			setColor(Color.red);
		}
	}
	public void onLinkAdded(Link link) {
		setColor(Color.red);
	}
	public void onLinkRemoved(Link link) {
		if (getNeighbors().isEmpty())
			setColor(Color.green);
	}

}
