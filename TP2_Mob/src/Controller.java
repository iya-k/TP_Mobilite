
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import io.jbotsim.core.Topology;
import io.jbotsim.core.event.CommandListener;
import io.jbotsim.ui.JTopology;
import io.jbotsim.ui.JViewer;
import io.jbotsim.ui.painting.BackgroundPainter;
import io.jbotsim.ui.painting.UIComponent;
import io.jbotsim.core.Node;
import io.jbotsim.core.Point;

public class Controller implements CommandListener, BackgroundPainter
{

	private Topology topology;
	private JTopology jTopology;
	private List<Point> points = new ArrayList<>();
	public static final String COMPUTE_TSP = "COMPUTE_TSP";
	
	public Controller() 
	{
		topology = new Topology();
		topology.setDefaultNodeModel(Target.class);
		
		initTopology();
		jTopology = new JTopology(topology);
		jTopology.addBackgroundPainter(this);
		
		//new JViewer(topology);
		new JViewer(jTopology);
		
		topology.addCommand(COMPUTE_TSP);
		topology.addCommandListener(this);
	}
	
	@Override
	public void onCommand(String command) 
	{
		if(command.contentEquals(COMPUTE_TSP)){
			points.clear();
			for(Node n: topology.getNodes()) {
				if(n instanceof Target) {
					points.add(n.getLocation());
				}
			}
			
			//List<Point> itineary = computeItineary();
			Algorithm algorithm = new Algorithm();
			algorithm.setPoints(points);
			List<Point> itineary = algorithm.nearestNeighbors();
			points = itineary;
			assignNode(itineary);
		}
		
	}
	
	
	protected void assignNode(List<Point> itineary) {
		if(!itineary.isEmpty()) {
			WaypointNode node = new WaypointNode();
			node.setLocation(itineary.get(0));
			for(int i = 1; i < itineary.size() + 1; i++) {
				node.addDestination(itineary.get(i % itineary.size()));
			}
			topology.addNode(node);
		}
	}
	
	public List<Point> computeItineary(){
	
		Algorithm algorithm = new Algorithm();
		algorithm.setPoints(points);
		return algorithm.getItineary();
	}
	
	@Override
	public void paintBackground(UIComponent uiComponent, Topology tp) 
	{
		Graphics2D g2d = (Graphics2D) uiComponent.getComponent();
		for(int i = 0; i< points.size(); i++) {
			Point s = points.get(i);
			Point d = points.get((i+1) % points.size());
			g2d.drawLine((int)s.x, (int)s.y, (int)d.x, (int)d.y);
		}
		
	}
	
	private void initTopology() {
		topology.addNode(58.0, 128.0);
		topology.addNode(157.0, 51.0);
		topology.addNode(224.0, 150.0);
		topology.addNode(339.0, 68.0);
		topology.addNode(460.0, 39.0);
		topology.addNode(537.0, 150.0);
		topology.addNode(568.0, 358.0);
		topology.addNode(458.0, 306.0);
		topology.addNode(222.0, 353.0);
		topology.addNode(113.0, 289.0);
		topology.addNode(437.0, 119.0);
		topology.addNode(360.0, 280.0);
		topology.addNode(128.0, 219.0);
		topology.addNode(271.0, 81.0);
		topology.addNode(504.0, 222.0);
	}
}
