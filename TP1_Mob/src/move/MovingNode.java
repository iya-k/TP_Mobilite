package move;

import io.jbotsim.core.Node;
public class MovingNode extends Node{
	public void onStart(){
		setDirection(Math.random()*2*Math.PI);
	}
	public void onClock(){
		move(1);
		wrapLocation();
	}
}