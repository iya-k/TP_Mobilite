package aggregation;

import io.jbotsim.core.Node;
import io.jbotsim.core.Topology;
import io.jbotsim.core.event.CommandListener;
import io.jbotsim.ui.JViewer;

public class MainAggregation {

	public static void main(String[] args) 
	{
		Topology tp = new Topology();
		tp.setDefaultNodeModel(SensorNodeBool.class);
		tp.setTimeUnit(500);
		new JViewer(tp);
		
		tp.addCommand("Sense values");
		tp.addCommand("Aggregate");
		//pareil que pour Computer_TSP du tp2, au lieu de faire implements, on peut directement le definir ici
		tp.addCommandListener(new CommandListener() 
		{
			@Override
			public void onCommand(String command) 
			{
				if(command.equals("Sense values"))
				{
					for(Node node: tp.getNodes())
					{
						((SensorNodeBool)node).sense();
					}
				}
				if(command.equals("Aggregate"))
				{
					for(Node node: tp.getNodes())
					{
						((SensorNodeBool)node).aggregate();
					}
				}
				
			}
		});
		
		tp.start();
		//ctrl + clique pour que l'execution commence
	}

}
