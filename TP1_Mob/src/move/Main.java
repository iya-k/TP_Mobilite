package move;
import io.jbotsim.core.Topology;
import io.jbotsim.ui.JViewer;

public class Main {

	public static void main(String[] args) {
		Topology tp = new Topology();
		tp.setDefaultNodeModel(MovingNode.class);
		new JViewer(tp);
		tp.start();

	}

}
