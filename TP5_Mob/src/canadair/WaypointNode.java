package canadair;
import io.jbotsim.core.Node;
import io.jbotsim.core.Point;

import java.util.LinkedList;
import java.util.Queue;

public class WaypointNode extends Node {
	protected double speed = 1; // Number of units to be moved in each step.
	protected Queue<Point> destinations = new LinkedList<Point>();

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void addDestination(Point point) {
		destinations.add(point);
	}

	@Override
	public void onClock() {
		super.onClock();
		if (!destinations.isEmpty()) {
			Point point = destinations.peek();
			double d = distance(point);
			if (d < speed) {
				setLocation(point);
				destinations.remove();
			} else {
				setDirection(point);
				move(speed);
			}
		}
	}

	public void onArrival() {

	}
}
