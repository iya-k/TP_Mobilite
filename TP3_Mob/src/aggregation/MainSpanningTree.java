package aggregation;

import io.jbotsim.core.Topology;
import io.jbotsim.ui.JViewer;

public class MainSpanningTree {

	public static void main(String[] args) 
	{
		Topology tp = new Topology();
		tp.setDefaultNodeModel(TreeNode.class);
		tp.setTimeUnit(500);
		new JViewer(tp);
		tp.start();
		//ctrl + clique pour que l'execution commence
	}

}
