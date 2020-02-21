import io.jbotsim.core.Topology;
import io.jbotsim.ui.JViewer;

public class MainTarget {

	public static void main(String[] args) 
	{
		Topology tp = new Topology();
		tp.setDefaultNodeModel(Target.class);
		new JViewer(tp);

	}

}
