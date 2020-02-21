package greedyrouting;

import java.util.ArrayList;
import java.util.List;

import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;
import io.jbotsim.core.Point;

public class RoutingNode extends Node {

	List<Node> parcourus;
	Node previous;
	GreedyMessage grm;

	@Override
	public void onMessage(Message message) {
		if (message.getContent() instanceof GreedyMessage) {
			grm = (GreedyMessage) message.getContent();
			previous = message.getSender();
			if (grm.target.equals(getLocation())) {
				System.out.println("Message receided");
			} else {
				initParcourus();
				route(grm);
			}
		} else {
			parcourus.remove(message.getSender());
			route(grm);
		}

	}

	public void initParcourus() {
		parcourus = new ArrayList<>();
		List<Node> neighbors = getNeighbors();
		for (Node n : neighbors) {
			parcourus.add(n);
		}

	}

	public void route(GreedyMessage m) {
		setColor(Color.red);
		Point myLocation = getLocation();
		Point target = m.target;

		Node nextHop = this;
		double minimum = myLocation.distance(target);
		for (Node n : parcourus) {
			Point p = n.getLocation();

			double d = p.distance(target);
			if (d < minimum) {
				minimum = d;
				nextHop = n;
			}
		}
		if (!nextHop.equals(this)) {
			send(nextHop, new Message(m));
		} else {
			setColor(null);
			send(previous, new Message("DeadLock"));
		}
	}

}
