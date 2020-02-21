package greedyrouting;

import java.util.List;

import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;
import io.jbotsim.core.Point;

public class GreedyRoutingNode extends Node 
{

	
	@Override
	public void onMessage(Message message) 
	{
		GreedyMessage m = (GreedyMessage) message.getContent();
		if(m.target.equals(getLocation()))
		{
			System.out.println("Message receided");
		}
		else {
			route(m);
		}
	}

	public void route(GreedyMessage m) 
	{
		setColor(Color.red);
		Point myLocation = getLocation();
		Point target = m.target;
		List<Node> neighbors = getNeighbors();
		
		Node nextHop = this;
		double minimum = myLocation.distance(target);
		for(Node n: neighbors)
		{
			Point p = n.getLocation();
			double d = p.distance(target);
			if(d < minimum)
			{
				minimum = d;
				nextHop = n;
			}
		}
		if(!nextHop.equals(this) )
		{
			send(nextHop, new Message(m));
		}
	}

	
}
