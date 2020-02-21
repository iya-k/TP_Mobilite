package greedyrouting;

import io.jbotsim.core.Color;
import io.jbotsim.core.Node;
import io.jbotsim.core.Topology;
import io.jbotsim.core.event.SelectionListener;
import io.jbotsim.core.event.StartListener;
import io.jbotsim.ui.JViewer;
import io.jbotsim.ui.icons.Icons;

public class MainRouting implements SelectionListener, StartListener{

	Topology tp;
	RoutingNode source;
	RoutingNode target;
	
	public MainRouting() {
		tp = new Topology();
		tp.setDefaultNodeModel(RoutingNode.class);
		tp.setTimeUnit(1000);
		tp.addSelectionListener(this);
		tp.addStartListener(this);
		new JViewer(tp);
		tp.start();
	}
	
	public static void main(String[] args) {
		new MainGreedyRouting();

	}

	@Override
	public void onSelection(Node node) {
		RoutingNode selectedNode = (RoutingNode) node;
		
		if(source == null) 
		{
			source = selectedNode;
			source.setColor(Color.red);
		}
		else
		{
			target = selectedNode;
			target.setIcon(Icons.FLAG);
			source.initParcourus();
			source.grm = new GreedyMessage(target.getLocation(), "Hello");
			source.route(source.grm);
		}
		
	}

	@Override
	public void onStart() 
	{
		if(target != null)
		{
			target.setIcon(Icons.DEFAULT_NODE_ICON);
		}
		source = null;
		for(Node n: tp.getNodes())
		{
			n.setColor(null);
		}
	}

	
}
